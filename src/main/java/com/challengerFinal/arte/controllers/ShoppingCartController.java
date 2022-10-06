package com.challengerFinal.arte.controllers;

import com.challengerFinal.arte.dtos.ShoppingCartDto;
import com.challengerFinal.arte.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping(value = "/shoppingCarts")
    public List<ShoppingCartDto> getOrderLineal() {
        return shoppingCartService.getAllShoppingCard();
    }
    @PostMapping("/shoppingCarts")
    public ResponseEntity<Object> createCart(
            Authentication authentication) {
        return shoppingCartService.createCart(authentication);
    }
}
