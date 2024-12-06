package com.backend.oopbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {
    int maxTicketCapacity;
    int totalTickets;
    int ticketReleaseRate;
    int customerRetrievalRate;
}
