package com.challengerFinal.arte.service;

import com.challengerFinal.arte.dtos.AddItemDTO;
import com.challengerFinal.arte.dtos.OrderRequestDto;
import com.challengerFinal.arte.model.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderService {
    OrderRequestDto getOrderRequest(Long id);
    List<OrderRequestDto> getOrderRequestsAll();
    OrderRequest saveRequest(OrderRequest orderRequest);
    ResponseEntity<Object> createOrder(
            @RequestParam String name, @RequestParam int cant, Authentication authentication
    );

    ResponseEntity<Object> deleteItem(@PathVariable("id") Long id);
    ResponseEntity<Object> addItem(Long id, AddItemDTO addItemDTO);

}
