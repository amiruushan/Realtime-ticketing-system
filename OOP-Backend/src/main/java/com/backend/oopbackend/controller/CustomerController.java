package com.backend.oopbackend.controller;

import com.backend.oopbackend.model.Customer;
import com.backend.oopbackend.service.CurrentCustomerManager;
import com.backend.oopbackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CurrentCustomerManager currentCustomerManager;

    @PostMapping("/login")
    public void login(@RequestParam String email, @RequestParam String password) {
        System.out.println(customerService.login(email, password));
    }

    @PostMapping("/signup")
    public Customer signup(@RequestBody Customer customer) {
        return customerService.signup(customer);
    }

    @PostMapping("/purchase_tickets")
    public void purchaseTickets(Customer customer, @RequestParam int ticketsPerPurchase){
        customer = currentCustomerManager.getCurrentCustomer();
        customer.setTicketsPerPurchase(ticketsPerPurchase);
        customerService.customerService(customer);
    }


}
