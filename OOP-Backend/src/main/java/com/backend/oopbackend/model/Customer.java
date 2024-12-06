package com.backend.oopbackend.model;

import com.backend.oopbackend.service.ConfigurationService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer")
public class Customer implements Runnable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "customer_name",nullable = false)
    private String customerName;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "customer_password", nullable = false)
    private String customerPassword;

    @Transient
    private int ticketsPerPurchase;
    @Transient
    private int retrievalInterval;
    @Transient
    private TicketPool ticketPool;

    // constructor for use in customer login

    public Customer(String customerName, String customerEmail, String customerPassword) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
    }

    @Override
    public void run() {
        ConfigurationService configurationService = new ConfigurationService();

        try{
            for(int i = 1; i <= ticketsPerPurchase; i++){
                System.out.println(
                        customerName + " purchased ticket " + i + " out of " + ticketsPerPurchase
                );
                ticketPool.removeTicket();

                Thread.sleep(configurationService.loadConfiguration().getCustomerRetrievalRate());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }
}
