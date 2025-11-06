package com.seif.carrental.repository;

import com.seif.carrental.model.Rental;
import com.seif.carrental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByCustomer(Customer customer);
}
