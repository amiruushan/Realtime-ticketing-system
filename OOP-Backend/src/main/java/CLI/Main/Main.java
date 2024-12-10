package CLI.Main;

import CLI.Configuration.Configuration;
import CLI.Customer;
import CLI.DatabaseHandling.VendorDAO;
import CLI.TicketPool;
import CLI.Vendor;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String userType = "v";
        int userLoginType = 0;
        String name = "";
        String password = "";
        String email = "";
        int releaseTickets = 0; // for ask to release tickets
        boolean loggedIn = false;

        Scanner sc = new Scanner(System.in);

        //configuration parameters getting to an onject
        Configuration configuration = new Configuration();
        try {
            configuration = configuration.loadFromJson("config.json");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //ticketPool shared instence
        TicketPool sharedPool = new TicketPool(configuration.getMaxTicketCapacity(), configuration.getTotalTickets());


        //vendor functioning
        while (userLoginType != 1 && userLoginType != 2) {
            try {
                System.out.println("Login or sign up...." +
                        "\n1. Login" +
                        "\n2. Sign up");
                userLoginType = sc.nextInt();
                sc.nextLine(); //to prevent new line error
            } catch (Exception e) {
                System.out.println("invalid input! Enter 1 or 2");
                sc.nextLine();
            }
        }

        if (userLoginType == 1) {
            System.out.println("Please enter your email: ");
            email = sc.nextLine();
            System.out.println("Please enter your password: ");
            password = sc.nextLine();

            // directing to the ticket pool to add tickets
            VendorDAO vendorDAO = new VendorDAO();
            Vendor vendor = vendorDAO.getVendorByEmail(email, password);

            if (vendor != null) {
                loggedIn = true;

            }
            //---------signup----------------
        }else {
            System.out.println("Please enter your name: ");
            name = sc.nextLine();
            System.out.println("Please enter your email: ");
            email = sc.nextLine();
            System.out.println("Please enter a password: ");
            password = sc.nextLine();

            //vendor object
            Vendor vendor1 = new Vendor(name, email, password);
            VendorDAO vendorDAO1 = new VendorDAO();
            vendorDAO1.saveVendor(vendor1);
            System.out.println("Your information has been saved in the database");
            loggedIn = true;

        }
        if (loggedIn) {
            while (releaseTickets == 1) {
                try {
                    System.out.println("Enter 1 to add tikcets to the Ticket Pool");
                    releaseTickets = sc.nextInt();
                    sc.nextLine();
                } catch (Exception e) {
                    System.out.println("");
                    sc.nextLine();
                }
            }


            // releasing tickets
            if (releaseTickets == 1 && loggedIn) {
                VendorDAO vendorDAO2 = new VendorDAO();
                Vendor v1 = vendorDAO2.getVendorIdByEmail(email, sharedPool);

                //CREATING MULTIPLE VENDORS
                Vendor v2 = new Vendor(001, "Black", 5, configuration.getTicketReleaseRate(), sharedPool);
                Vendor v3 = new Vendor(002, "Mark", 7, configuration.getTicketReleaseRate(), sharedPool);
                // asking the number of tickets going  to be released
                System.out.print("Enter the number of tickets you want to add in vendor 1 : ");
                int ticketsPerRelease = sc.nextInt();
                sc.nextLine();
                v1.setTicketPerRelease(ticketsPerRelease);


                // ticket release interval

                int releaseInterval = configuration.getTicketReleaseRate();
                v1.setReleaseInterval(releaseInterval);

                //ticket pool
                v1.setTicketPool(sharedPool);

                Thread t1 = new Thread(v1);
                Thread t2 = new Thread(v2);
                Thread t3 = new Thread(v3);
                t1.start();
                t2.start();
                t3.start();

                // wait for thread to finish
                try {
                    t1.join();
                    t2.join();
                    t3.join();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                // CUSTOMER THREADS
                Thread c1 = new Thread(new Customer(01, "Tom", configuration.getCustomerRetrievalRate(), sharedPool));
                Thread c2 = new Thread(new Customer(02, "Jack", configuration.getCustomerRetrievalRate(), sharedPool));

                c1.start();
                c2.start();

                // wait for finish buying tickets
                try {
                    c1.join();
                    c2.join();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                sharedPool.printTicketQ();

                //TOTAL TICKETS
                System.out.println(sharedPool.getTotalTicket());
            }
        } else {
            System.out.println("Invalid details0");
        }
    }

}
