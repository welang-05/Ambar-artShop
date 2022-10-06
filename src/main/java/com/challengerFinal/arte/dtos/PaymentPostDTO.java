package com.challengerFinal.arte.dtos;

import java.util.List;

public class PaymentPostDTO {
    private Long id;
    private String name;
    private List<Integer> payments;

    public PaymentPostDTO(Long id, String name, List<Integer> payments) {
        this.id = id;
        this.name = name;
        this.payments = payments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }
}
