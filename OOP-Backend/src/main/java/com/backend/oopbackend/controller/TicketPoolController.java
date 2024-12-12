package com.backend.oopbackend.controller;

import com.backend.oopbackend.model.TicketPool;
import com.backend.oopbackend.service.TicketPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticketpool")
public class TicketPoolController {

    @Autowired
    private TicketPoolService ticketPoolService;
    @Autowired
    private DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    @GetMapping("/totaltickets") //returns the no of total tickets
    public int getTotalTickets() {
        TicketPool ticketPool = ticketPoolService.getTicketPool();
        if(ticketPool != null) {
            System.out.println(ticketPool.getTotalTickets());
            return ticketPool.getTotalTickets();
        }else {
            return 0;
        }
    }

    @GetMapping("/maxtickets")
    public int getMaxTickets() {
        TicketPool ticketPool = ticketPoolService.getTicketPool();
        if(ticketPool != null) {
            System.out.println("Max Tickets : " + ticketPool.getMaxTickets());
            return ticketPool.getMaxTickets();
        }else {
            return 0;
        }
    }

}
