package com.seif.carrental.controller;

import com.seif.carrental.model.Payment;
import com.seif.carrental.service.PaymentService;
import com.seif.carrental.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentController {

    private final PaymentService paymentService;
    private final RentalService rentalService;

    public PaymentController(
            PaymentService paymentService,
            RentalService rentalService
    ) {
        this.paymentService = paymentService;
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        payment.setRental(rentalService.getRentalById(payment.getRental().getId()).orElse(null));
        return paymentService.savePayment(payment);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}
