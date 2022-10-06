package com.challengerFinal.arte.dtos;

import com.challengerFinal.arte.model.Payment;

import java.io.Serializable;
import java.util.List;

public class PaymentDto implements Serializable {
    private Long id;
    private String name;
    private List<Integer> payments;

    public PaymentDto(Payment payment) {
        this.id = payment.getId();
        this.name = payment.getName();
        this.payments = payment.getPayments();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getPayments() {
        return payments;
    }
}
