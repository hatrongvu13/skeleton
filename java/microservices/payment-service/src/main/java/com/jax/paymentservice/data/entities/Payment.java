package com.jax.paymentservice.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    private int paymentId;
    private String paymentStatus;
    private String transactionId;
}
