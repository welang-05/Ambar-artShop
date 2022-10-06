package com.challengerFinal.arte.service;

import com.challengerFinal.arte.dtos.PaymentDto;
import com.challengerFinal.arte.dtos.PaymentPostDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> getAllPayments();

    ResponseEntity<Object> createPayment(PaymentPostDTO paymentPostDTO);

    ResponseEntity<Object> updatePayment(@RequestBody PaymentPostDTO paymentPostDTO,
                                         @PathVariable ("id") Long id);

    ResponseEntity<Object> patchPayment(@RequestBody PaymentPostDTO paymentPostDTO,
                                        @PathVariable ("id") Long id);

    ResponseEntity<Object> deletePayment(@PathVariable("id")Long id);
}
