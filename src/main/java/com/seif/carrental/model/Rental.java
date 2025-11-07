package com.seif.carrental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference(value = "car-rentals")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference(value = "customer-rentals")
    private Customer customer;

    @OneToOne(mappedBy = "rental", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "rental-payment")
    private Payment payment;
}
