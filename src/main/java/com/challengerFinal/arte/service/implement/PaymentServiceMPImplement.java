package com.challengerFinal.arte.service.implement;

import com.challengerFinal.arte.dtos.ItemDTO;
import com.challengerFinal.arte.model.Product;
import com.challengerFinal.arte.payments.PaymentUtils;
import com.challengerFinal.arte.payments.Preference;
import com.challengerFinal.arte.repositories.ProductRepository;
import com.challengerFinal.arte.service.PaymentServiceMP;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
@Service
public class PaymentServiceMPImplement implements PaymentServiceMP {
    @Autowired
    ProductRepository productRepository;
    @Override
    public ResponseEntity<Object> getPaymentUrl(@NotNull List<ItemDTO> itemDTOList) {
        itemDTOList.forEach(itemDTO -> {
            Product productInfo =  productRepository.findById(Long.parseLong(itemDTO.getId())).orElse(null);
            itemDTO.setDescription(productInfo.getDescription());
            itemDTO.setTitle(productInfo.getName());
            itemDTO.setCurrency_id("COP");
            itemDTO.setPicture_url("");
            itemDTO.setCategory_id("art");
            itemDTO.setUnit_price(productInfo.getPrice());
        });
        Preference preference = new Preference(itemDTOList);
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = "";
        try {
            requestBody = mapper.writeValueAsString(preference);
            System.out.println(requestBody);
        } catch (JsonProcessingException e) {
            System.out.println(e);
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        try {
            String linkResponse = PaymentUtils.postGetMPUrl(requestBody);
            return new ResponseEntity<Object>(linkResponse ,HttpStatus.CREATED);
        } catch (IOException e) {
            System.out.println(e);
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
