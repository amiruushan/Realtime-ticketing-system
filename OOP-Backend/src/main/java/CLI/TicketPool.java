package CLI;

import CLI.Configuration.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketPool {
    private int maxTicket;
    private int totalTicket;
    private String ticketId;
    private String vendorName;

    private Queue<Ticket> ticketsQueue = new LinkedList<Ticket>();

    public TicketPool(int maxTicket, int totalTicket ) {
        this.maxTicket = maxTicket;
        this.totalTicket = totalTicket;
    }

    // METHOD TO ADD TICKETS
    public synchronized boolean addTicket(Ticket ticket) {
        if(ticketsQueue.size() < maxTicket) {
            ticketsQueue.add(ticket); // adding ticket object to the Queue
            return false;
//          totalTicket++;
        }else {
            System.out.println("Ticket pool is full");
            return true; /* to consider if the pool is full by initializing
                            this method in to a variable called "isFull" */
        }
    }

    // METHOD TO BUY TICKETS
    public synchronized Ticket removeTicket(){
        if(ticketsQueue.size() > 0) {
            Ticket ticket = ticketsQueue.poll(); // removes the first object in the queue
            return ticket;
        }
        else {
            System.out.println("Ticket pool is empty");
            return null;
        }
    }

    public void loadFromConfiguration(Configuration config) {
        this.maxTicket = config.getMaxTicketCapacity();
        this.totalTicket = config.getTotalTickets();
    }

    // test method to see the queue
    public void printTicketQ(){
        System.out.println(ticketsQueue);
    }



    public synchronized int getTotalTicket() {
        return totalTicket + ticketsQueue.size();
    }

}
