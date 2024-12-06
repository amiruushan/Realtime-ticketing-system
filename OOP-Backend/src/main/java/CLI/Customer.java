package CLI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Customer implements Runnable {
    private int customerId;
    private String customerName;
    private int retreivalInterval;
    private TicketPool ticketPool;


    @Override
    public void run() {
        try {
            while(true) {
                Ticket ticket = ticketPool.removeTicket();
                if(ticket != null) {
                    System.out.println("Customer " + customerName + " Purchased the ticket " + ticket.getTicketID());
                }else {
                    break;
                }
                Thread.sleep(retreivalInterval);
            }

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

