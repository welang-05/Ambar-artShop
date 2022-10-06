package com.challengerFinal.arte.controllers;

import com.challengerFinal.arte.dtos.ItemDTO;
import com.challengerFinal.arte.payments.PaymentUtils;
import com.challengerFinal.arte.service.PaymentServiceMP;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PaymentControllerMp {
    @Autowired
    PaymentServiceMP paymentServiceMP;

    @PostMapping(value = "/paymentmp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> generateUrl(@RequestBody String items) throws Exception {
        List<ItemDTO> itemDTOList = PaymentUtils.stringToDTO(items);
        return paymentServiceMP.getPaymentUrl(itemDTOList);
    }
}
