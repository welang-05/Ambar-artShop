package com.challengerFinal.arte.controllers;

import com.challengerFinal.arte.dtos.PaymentDto;
import com.challengerFinal.arte.dtos.PaymentPostDTO;
import com.challengerFinal.arte.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping("/payment")
    public List<PaymentDto> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @PostMapping("/payment")
    public ResponseEntity<Object> createPayment(
            @RequestBody PaymentPostDTO paymentPostDTO) {
        return paymentService.createPayment(paymentPostDTO);
    }

    @PutMapping("/payment/update/{id}")
    public ResponseEntity<Object> updatePayment(@RequestBody PaymentPostDTO paymentPostDTO,
                                                @PathVariable ("id") Long id) {
        return paymentService.updatePayment(paymentPostDTO, id);

    }
    @PatchMapping("/payment/update/{id}")
    public ResponseEntity<Object> patchPayment(@RequestBody PaymentPostDTO paymentPostDTO,
                                               @PathVariable ("id") Long id){
        return paymentService.patchPayment(paymentPostDTO, id);
          }
    @DeleteMapping("payment/delete/{id}")
    public ResponseEntity<Object> deletePayment(@PathVariable("id")Long id){
        return paymentService.deletePayment(id);
    }
}
