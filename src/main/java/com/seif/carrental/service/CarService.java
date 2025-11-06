package com.seif.carrental.service;

import com.seif.carrental.model.Car;
import com.seif.carrental.model.CarStatus;
import com.seif.carrental.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Car saveCar(Car car) {
        // Default new car status
        if (car.getStatus() == null) {
            car.setStatus(CarStatus.AVAILABLE);
        }
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public List<Car> getAvailableCars() {
        return carRepository.findByStatus(CarStatus.AVAILABLE);
    }

    public List<Car> searchByBrand(String brand) {
        return carRepository.findByBrandContainingIgnoreCase(brand);
    }

    public Car updateStatus(Long id, CarStatus status) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        car.setStatus(status);
        return carRepository.save(car);
    }
}
