package com.seif.carrental.repository;

import com.seif.carrental.model.Car;
import com.seif.carrental.model.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByStatus(CarStatus status);
    List<Car> findByBrandContainingIgnoreCase(String brand);
}
