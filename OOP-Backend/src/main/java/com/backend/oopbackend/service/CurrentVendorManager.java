package com.backend.oopbackend.service;

import com.backend.oopbackend.model.Vendor;
import org.springframework.stereotype.Component;

@Component
public class CurrentVendorManager {

    private Vendor currentVendor;

    public Vendor getCurrentVendor() {
        return currentVendor;
    }

    public void setCurrentVendor(Vendor vendor) {
        this.currentVendor = vendor;
    }
}
