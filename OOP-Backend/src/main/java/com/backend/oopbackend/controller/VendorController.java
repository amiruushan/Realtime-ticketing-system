package com.backend.oopbackend.controller;

import CLI.Ticket;
import com.backend.oopbackend.model.Vendor;
import com.backend.oopbackend.service.CurrentVendorManager;
import com.backend.oopbackend.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private CurrentVendorManager currentVendorManager;

    @PostMapping("/login")
    public void login(@RequestParam String email, @RequestParam String password) {
        System.out.println(vendorService.login(email, password));
    }

    @PostMapping("/signup")
    public Vendor signup(@RequestBody Vendor vendor) {
        return vendorService.signup(vendor);
    }


    @PostMapping("/addticket")
    public void addTicket (Vendor vendor, @RequestParam int ticketPerRelease) {
        vendor = currentVendorManager.getCurrentVendor();
        vendor.setTicketPerRelease(ticketPerRelease);
        vendorService.vendorService(vendor);
    }
}
