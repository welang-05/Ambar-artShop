package com.challengerFinal.arte.service.implement;

import com.challengerFinal.arte.dtos.ShoppingCartDto;
import com.challengerFinal.arte.model.Client;
import com.challengerFinal.arte.model.ShoppingCart;
import com.challengerFinal.arte.repositories.ClientRepository;
import com.challengerFinal.arte.repositories.ShoppingCartRepository;
import com.challengerFinal.arte.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartImplement implements ShoppingCartService {
    @Autowired
    ShoppingCartRepository orderRepository;
    @Autowired
    ClientRepository clientRepository;
    @Override
    public List<ShoppingCartDto> getAllShoppingCard() {
        return orderRepository.findAll().stream().map(ShoppingCartDto::new).collect(Collectors.toList());
    }

    @Override
    public ShoppingCart saveShoppingCard(ShoppingCart shoppingCart) {
        return orderRepository.save(shoppingCart);
    }

    @Override
    public ResponseEntity<Object> createCart(Authentication authentication) {
        Client clientConected = clientRepository.findByEmail(authentication.getName());
        ShoppingCart shoppingCartNow = new ShoppingCart(clientConected);
        shoppingCartNow.setActive(true);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
