package com.backend.oopbackend.service;

import com.backend.oopbackend.model.Customer;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CurrentCustomerManager {

    public Customer currentCustomer;
}
