package com.backend.oopbackend.repository;

import com.backend.oopbackend.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

    //finding for a registered vendor for the vendor given data in db, when vendor login.
    Vendor findByVendorEmail(String email); // this will return null if no records match to the mail
}
