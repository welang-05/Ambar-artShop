package com.challengerFinal.arte.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class GoodsReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator ="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private Double totalPrice;
    private Boolean status;
    private LocalDate date;
    private Integer payments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shoppingCart")
    private ShoppingCart shoppingCart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public GoodsReceipt() {
    }

    public GoodsReceipt(Double totalPrice, Boolean status, LocalDate date, Integer payments, ShoppingCart goodsReceipts, Payment payment) {
        this.totalPrice = totalPrice;
        this.status = status;
        this.date = date;
        this.payments = payments;
        this.shoppingCart = goodsReceipts;
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }


    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPayments() {
        return payments;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "GoodsReceipt{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", date=" + date +
                ", payments=" + payments +
                ", shoppingCart=" + shoppingCart +
                ", payment=" + payment +
                '}';
    }
}
