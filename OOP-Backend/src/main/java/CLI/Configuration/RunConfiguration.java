package CLI.Configuration;

import java.io.IOException;
import java.util.Scanner;

public class RunConfiguration {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int maxTicketCapacity = 0;
        int totalTickets = 0;
        int ticketReleaseRate = 0;
        int customerRetrievalRate = 0;

        // max ticket capacity
        try {
            System.out.print("Enter the maximum ticket capacity: ");
            maxTicketCapacity = sc.nextInt();
            sc.nextLine();
        }catch (Exception e){
            System.out.println("Please enter a valid maximum ticket capacity.");
        }

        // total number of tickets
        do {
            try {
                System.out.print("Enter the total number of tickets: ");
                totalTickets = sc.nextInt();
                sc.nextLine();
                if (totalTickets < maxTicketCapacity) {
                    break;
                } else {
                    System.out.println("Total number of tickets must be less than maximum ticket capacity.");
                }
            }catch (Exception e){
                System.out.println("Please enter a valid total number of tickets.");
            }
        }while (totalTickets > maxTicketCapacity);

        // ticket release rate
        try {
            System.out.print("Enter the ticket release rate: ");
            ticketReleaseRate = sc.nextInt();
            sc.nextLine();
        }catch (Exception e){
            System.out.println("Please enter a valid ticket release rate.");
        }

        // customer retreival rate
        try {
            System.out.print("Enter the customer retrieval rate: ");
            customerRetrievalRate = sc.nextInt();
            sc.nextLine();
        }catch (Exception e){
            System.out.println("Please enter a valid customer retrieval rate.");
        }

        Configuration config = new Configuration(maxTicketCapacity,totalTickets,ticketReleaseRate,customerRetrievalRate);
        config.saveToJson("config.json");

    }
}
