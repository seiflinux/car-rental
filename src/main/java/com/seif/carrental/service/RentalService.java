package com.seif.carrental.service;

import com.seif.carrental.model.*;
import com.seif.carrental.repository.CarRepository;
import com.seif.carrental.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;

    public RentalService(RentalRepository rentalRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public Rental saveRental(Rental rental) {
        // Calculate total price
        long days = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());
        if (days <= 0) throw new RuntimeException("Invalid rental duration");

        Car car = rental.getCar();
        if (car.getStatus() != CarStatus.AVAILABLE) {
            throw new RuntimeException("Car is not available for rent");
        }

        double totalPrice = days * car.getPricePerDay();
        rental.setTotalPrice(totalPrice);

        // Mark car as RENTED
        car.setStatus(CarStatus.RENTED);
        carRepository.save(car);

        return rentalRepository.save(rental);
    }

    public void deleteRental(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
        Car car = rental.getCar();
        car.setStatus(CarStatus.AVAILABLE);
        carRepository.save(car);
        rentalRepository.delete(rental);
    }
}
