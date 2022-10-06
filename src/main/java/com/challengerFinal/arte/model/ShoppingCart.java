package com.challengerFinal.arte.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(strategy = "native",name = "native")
    private Long id;
    private boolean active;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client")
    private Client client;
    @OneToMany(mappedBy = "shoppingCart",
            fetch = FetchType.EAGER)
    Set <OrderRequest> orderRequest = new HashSet<>();

    @OneToMany(mappedBy= "shoppingCart",
            fetch=FetchType.EAGER)
    Set<GoodsReceipt> goodsReceipts = new HashSet<>();

    public ShoppingCart() {
    }

    public ShoppingCart(Client client) {
        this.client = client;
        this.active = true;
    }


    public Long getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<OrderRequest> getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(Set<OrderRequest> orderRequest) {
        this.orderRequest = orderRequest;
    }

    public Set<GoodsReceipt> getGoodsReceipts() {
        return goodsReceipts;
    }

    public void setGoodsReceipts(Set<GoodsReceipt> goodsReceipts) {
        this.goodsReceipts = goodsReceipts;
    }

    public void addGoodsReceipt(GoodsReceipt goodsReceipt){
        goodsReceipt.setShoppingCart(this);
        goodsReceipts.add(goodsReceipt);
    }

    public void addOrder(OrderRequest orderReques){
        orderReques.setShoppingCart(this);
        orderRequest.add(orderReques);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", isShoppingCart=" + active +
                ", client=" + client +
                ", orderRequest=" + orderRequest +
                ", goodsReceipts=" + goodsReceipts +
                '}';
    }
}
