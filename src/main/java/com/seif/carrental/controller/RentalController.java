package com.seif.carrental.controller;

import com.seif.carrental.model.Rental;
import com.seif.carrental.service.CarService;
import com.seif.carrental.service.CustomerService;
import com.seif.carrental.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@CrossOrigin("*")
public class RentalController {

    private final RentalService rentalService;
    private final CustomerService customerService;
    private final CarService carService;

    public RentalController(
            RentalService rentalService,
            CustomerService customerService,
            CarService carService
    ) {
        this.rentalService = rentalService;
        this.customerService = customerService;
        this.carService = carService;
    }

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
    }

    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
        rental.setCustomer(customerService.getCustomerById(rental.getCustomer().getId()).orElse(null));
        rental.setCar(carService.getCarById(rental.getCar().getId()).orElse(null));
        return rentalService.saveRental(rental);
    }

    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
    }
}
