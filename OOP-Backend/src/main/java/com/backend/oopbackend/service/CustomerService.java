package com.backend.oopbackend.service;

import com.backend.oopbackend.model.Customer;
import com.backend.oopbackend.model.TicketPool;
import com.backend.oopbackend.repository.CustomerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired  //connecting repository
    private CustomerRepository customerRepository;

    @Autowired //Connecting CustomerManager class to assign and get the curent customer
    private CurrentCustomerManager currentCustomerManager;

    @Autowired
    private TicketPoolService ticketPoolService;


    // Customer signup method
    public Customer signup(Customer customer){
        Customer existingCustomer = customerRepository.findByCustomerEmail(customer.getCustomerEmail());
        if(existingCustomer != null){
            throw new RuntimeException("This email is already registered to the system...");
        }

        // saving customer to the database
        Customer saveCustomer = customerRepository.save(customer);

        // saving the created customer object in the CurrentCustomerManager class after sign up
        currentCustomerManager.setCurrentCustomer(saveCustomer);
        return saveCustomer;
    }

    // Customer login
    public Customer login(String email, String password){
        Customer existingCustomer = customerRepository.findByCustomerEmail(email);

        if(existingCustomer != null){
            if(existingCustomer.getCustomerPassword().equals(password)){
                // saving the created customer object in the CurrentVendorManager
                currentCustomerManager.setCurrentCustomer(existingCustomer);
                return existingCustomer;
            }else{
                throw new RuntimeException("This password is incorrect");
            }
        }else {
            throw new RuntimeException("This email is incorrect");
        }
    }


    // getting the loged or signed up customer object to purchase tickets
    public Customer getCurrentCustomer(){
        Customer customer = currentCustomerManager.getCurrentCustomer();
        if (customer == null) {
            throw new RuntimeException("Login or signup to purchase tickets!");
        }
        return customer;
    }


    public void customerService(Customer customer) {
        TicketPool ticketPool= ticketPoolService.getTicketPool();
        customer.setTicketPool(ticketPool);

        Thread customerThread = new Thread(customer);
        customerThread.start();
    }
}
