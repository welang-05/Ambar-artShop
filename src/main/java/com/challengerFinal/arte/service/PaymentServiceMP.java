package com.challengerFinal.arte.service;

import com.challengerFinal.arte.dtos.ItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PaymentServiceMP {
    ResponseEntity<Object> getPaymentUrl(List<ItemDTO> itemDTOList);
}
