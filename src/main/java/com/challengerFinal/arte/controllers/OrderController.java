package com.challengerFinal.arte.controllers;

import com.challengerFinal.arte.dtos.AddItemDTO;
import com.challengerFinal.arte.dtos.OrderRequestDto;
import com.challengerFinal.arte.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/orders")
    public List<OrderRequestDto> getOrderRequestsAll() {
        return orderService.getOrderRequestsAll();
    }

    @PostMapping("/addItemToCart")
    public ResponseEntity<Object> createOrder(
            @RequestParam String name,
            @RequestParam int cant,
            Authentication authentication) {
        return orderService.createOrder(name, cant, authentication);
    }

    @DeleteMapping("/deleteItemFromCart/{id}")
    public ResponseEntity<Object> deleteItem(
            @PathVariable("id") Long id){
        return orderService.deleteItem(id);
    }
    @PatchMapping("/update/items/{id}")
    public ResponseEntity<Object> addItem(@RequestBody AddItemDTO addItemDTO,
                                          @PathVariable ("id") Long id){
        return orderService.addItem(id, addItemDTO);
    }

}
