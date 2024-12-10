package com.backend.oopbackend.service;

import com.backend.oopbackend.model.TicketPool;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TicketPoolService {

    @Getter
    private TicketPool ticketPool;

    @Autowired
    private ConfigurationService configurationService;


    @PostConstruct
    public void initializeTicketPool()  {  // creating a ticketpool object by getting values from json file
        try {
            ticketPool =  new TicketPool(
                    configurationService.loadConfiguration().getMaxTicketCapacity(),
                    configurationService.loadConfiguration().getTotalTickets()
                    );
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }

}