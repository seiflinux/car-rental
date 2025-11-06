package com.seif.carrental.controller;

import com.seif.carrental.model.Car;
import com.seif.carrental.model.CarStatus;
import com.seif.carrental.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin("*")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carService.getCarById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        Car car = carService.getCarById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        updatedCar.setId(car.getId());
        return carService.saveCar(updatedCar);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @GetMapping("/available")
    public List<Car> getAvailableCars() {
        return carService.getAvailableCars();
    }

    @PatchMapping("/{id}/status")
    public Car updateStatus(@PathVariable Long id, @RequestParam CarStatus status) {
        return carService.updateStatus(id, status);
    }
}
