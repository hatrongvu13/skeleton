package com.jax.paymentservice.api.controllers;

import com.jax.paymentservice.api.services.PaymentService;
import com.jax.paymentservice.data.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(value = "/pay")
    public Payment doPayment(@RequestBody Payment payment) {
        return paymentService.doPayment(payment);
    }
}
