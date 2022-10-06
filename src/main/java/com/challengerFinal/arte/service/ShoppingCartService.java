package com.challengerFinal.arte.service;

import com.challengerFinal.arte.dtos.ShoppingCartDto;
import com.challengerFinal.arte.model.ShoppingCart;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCartDto> getAllShoppingCard();
    ShoppingCart saveShoppingCard(ShoppingCart shoppingCart);
    ResponseEntity<Object> createCart(Authentication authentication);
}
