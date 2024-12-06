package com.backend.oopbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketPool {
    private int maxTickets;
    private int totalTickets;

    public void addTicket(){
        if(totalTickets < maxTickets) {
            totalTickets++;
        }else{
            System.out.println("Can't add ticket to the pool... Ticket pool is full");
        }
    }

    public void removeTicket(){
        if(totalTickets > 0) {
            totalTickets--;
        }else {
            System.out.println("Can't remove ticket from the pool... Ticket pool is empty");
        }
    }
}


