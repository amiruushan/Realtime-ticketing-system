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
@Table(name = "vendor")
public class Vendor implements Runnable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "vendor_id")
    private int vendorId;

    @Column(name = "vendor_name",nullable = false)
    private String vendorName;

    @Column(name = "vendor_email",nullable = false)
    private String vendorEmail;

    @Column(name = "vendor_password", nullable = false)
    private String vendorPassword;

    @Transient
    private int ticketPerRelease;
    @Transient
    private int releaseInterval;
    @Transient
    private TicketPool ticketPool;

    public Vendor(String vendorName, String vendorEmail, String vendorPassword) {
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
        this.vendorPassword = vendorPassword;
    }

    @Override
    public void run() {
        ConfigurationService configurationService = new ConfigurationService();
        try {
            for (int i = 1; i <= ticketPerRelease; i++) {
                System.out.println(
                        "Vendor " + vendorName + " added ticket " + i + " out of " + ticketPerRelease
                );
                ticketPool.addTicket();

                Thread.sleep(configurationService.loadConfiguration().ticketReleaseRate);
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
