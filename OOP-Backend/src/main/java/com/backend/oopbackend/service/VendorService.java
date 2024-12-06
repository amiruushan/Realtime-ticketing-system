package com.backend.oopbackend.service;

import com.backend.oopbackend.model.TicketPool;
import com.backend.oopbackend.model.Vendor;
import com.backend.oopbackend.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private CurrentVendorManager currentVendorManager;
    @Autowired
    private TicketPoolService ticketPoolService;

    // vendor signup method
    public Vendor signup(Vendor vendor){
        Vendor existingVendor = vendorRepository.findByVendorEmail(vendor.getVendorEmail());
        if(existingVendor != null){
            throw new RuntimeException("This email is already registered to the system...");
        }

        // saving ventdor to the database
        Vendor saveVendor = vendorRepository.save(vendor);

        // saving the created vendor object in the CurrentVendorManager class after sign up
        currentVendorManager.setCurrentVendor(saveVendor);
        return saveVendor;
    }

    // vendor login
    public Vendor login(String email, String password){
        Vendor existingVendor = vendorRepository.findByVendorEmail(email);

        if(existingVendor != null){
            if(existingVendor.getVendorPassword().equals(password)){
                // saving the created vendor object in the CurrentVendorManager
                currentVendorManager.setCurrentVendor(existingVendor);
                return existingVendor;
            }else{
                throw new RuntimeException("This password is incorrect");
            }
        }else {
            throw new RuntimeException("This email is incorrect");
        }
    }

    // getting the loged or signed up vendor object to add tickets
    public Vendor getCurrentVendor(){
        Vendor vendor = currentVendorManager.getCurrentVendor();
        if (vendor == null) {
            throw new RuntimeException("Login or signup to add tickets!");
        }
        return vendor;
    }

    public void vendorService(Vendor vendor) {
        TicketPool ticketPool= ticketPoolService.getTicketPool();
        vendor.setTicketPool(ticketPool);

        Thread vendorThread = new Thread(vendor);
        vendorThread.start();
    }
}
