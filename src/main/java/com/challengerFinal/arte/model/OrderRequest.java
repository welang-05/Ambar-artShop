package com.challengerFinal.arte.model;

import com.challengerFinal.arte.model.enums.StatePedido;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class OrderRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(strategy = "native",name = "native")
    private Long id;
    private LocalDate date;
    private StatePedido state;
    private Integer units;
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderRequest_id")
    private ShoppingCart shoppingCart;
    public OrderRequest(){}

    public OrderRequest(Product product, LocalDate date, StatePedido state, Double price, Integer units, ShoppingCart shoppingCart) {
        this.product = product;
        this.date = date;
        this.state = state;
        this.price = price;
        this.units = units;
        this.shoppingCart = shoppingCart;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public StatePedido getState() {
        return state;
    }

    public void setState(StatePedido state) {
        this.state = state;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "id=" + id +
                ", date=" + date +
                ", state=" + state +
                ", units=" + units +
                ", price=" + price +
                ", product=" + product +
                ", shoppingCart=" + shoppingCart +
                '}';
    }
}
