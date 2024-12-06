package CLI.Configuration;

import java.io.IOException;
import java.util.Scanner;

public class RunConfiguration {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int maxTicketCapacity;
        int totalTickets;
        int ticketReleaseRate;
        int customerRetrievalRate;

        System.out.print("Enter the maximum ticket capacity: ");
        maxTicketCapacity = sc.nextInt();
        sc.nextLine();

        do {
            System.out.print("Enter the total number of tickets: ");
            totalTickets = sc.nextInt();
            sc.nextLine();
            if (totalTickets < maxTicketCapacity) {
                break;
            }else{
                System.out.println("Total number of tickets must be less than maximum ticket capacity.");
            }
        }while (totalTickets > maxTicketCapacity);

        System.out.print("Enter the ticket release rate: ");
        ticketReleaseRate = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter the customer retrieval rate: ");
        customerRetrievalRate = sc.nextInt();
        sc.nextLine();

        Configuration config = new Configuration(maxTicketCapacity,totalTickets,ticketReleaseRate,customerRetrievalRate);
        config.saveToJson("config.json");

        //Test the parameters are passing into TicketPool.java
//        TicketPool ticketPool = new TicketPool();
//        ticketPool.loadFromConfiguration(config.loadFromJson("config.json"));
//        System.out.println("Max ticket capacity: " + ticketPool.getMaxTicket());


    }
}
