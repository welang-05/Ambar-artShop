package com.challengerFinal.arte.dtos;

import com.challengerFinal.arte.model.ShoppingCart;

import java.io.Serializable;

public class ShoppingCartDto implements Serializable {
    private Long id;

    public ShoppingCartDto(ShoppingCart shoppingCart) {
        this.id = shoppingCart.getId();
    }

    public Long getId() {
        return id;
    }

}
