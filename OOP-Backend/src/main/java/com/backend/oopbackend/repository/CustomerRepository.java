package com.backend.oopbackend.repository;

import com.backend.oopbackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // searching for an existing customer relevant to the details providing when customer login
    Customer findByCustomerEmail(String email);
}
