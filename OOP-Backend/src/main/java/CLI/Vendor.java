package CLI;

import CLI.Configuration.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, toString(), etc.
@NoArgsConstructor
@AllArgsConstructor

public class Vendor implements Runnable {

    private int VendorId;
    private String vendorName;
    private String vendorEmail;
    private String vendorPassword;
    private int ticketPerRelease;
    private int releaseInterval; // in milliseconds
    private TicketPool ticketPool;


    public Vendor(int vendorId, String vendorName, int ticketPerRelease, int releaseInterval, TicketPool ticketPool) {
        VendorId = vendorId;
        this.vendorName = vendorName;
        this.ticketPerRelease = ticketPerRelease;
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketPool;
    }

    // constructor using for vendor signup
    public Vendor(String vendorName, String vendorEmail, String vendorPassword) {
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
        this.vendorPassword = vendorPassword;
    }

    @Override
    public void run() {
        int ticketCounter = 1;

        try {
            // generate the tickers
            for (int i = 1; i <= ticketPerRelease; i++) {
                //ticketID generation
                String ticketId = "T" + getVendorId() + "_" + ticketCounter;
                ticketCounter++;

                //adding ticket to the ticketpool
                Ticket ticket = new Ticket(ticketId, vendorName);
                boolean isFull = ticketPool.addTicket(ticket);

                // stop when ticket pool full
                if(isFull) {
                    System.out.println("Ticket pool is Full!");
                    break;
                }

                System.out.println("Vendor " + getVendorName() + " added ticket: " + ticketId);
                Thread.sleep(releaseInterval);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
