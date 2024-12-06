package com.backend.oopbackend.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CurrentCustomerManager {

    private Customer currentCustomer;
}
