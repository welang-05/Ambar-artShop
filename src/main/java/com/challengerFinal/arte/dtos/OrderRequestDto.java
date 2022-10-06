package com.challengerFinal.arte.dtos;

import com.challengerFinal.arte.model.OrderRequest;
import com.challengerFinal.arte.model.enums.StatePedido;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderRequestDto implements Serializable {
    private Long id;
    private LocalDate date;
    private StatePedido state;
    private Integer units;
    private Double price;

    public OrderRequestDto(OrderRequest orderRequest) {
        this.id = orderRequest.getId();
        this.date = orderRequest.getDate();
        this.state = orderRequest.getState();
        this.units = orderRequest.getUnits();
        this.price = orderRequest.getPrice();

    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public StatePedido getState() {
        return state;
    }

    public Integer getUnits() {
        return units;
    }

    public Double getPrice() {
        return price;
    }
}
