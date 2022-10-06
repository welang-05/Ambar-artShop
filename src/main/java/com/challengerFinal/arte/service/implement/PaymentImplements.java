package com.challengerFinal.arte.service.implement;

import com.challengerFinal.arte.dtos.PaymentDto;
import com.challengerFinal.arte.dtos.PaymentPostDTO;
import com.challengerFinal.arte.model.GoodsReceipt;
import com.challengerFinal.arte.model.Payment;
import com.challengerFinal.arte.repositories.GoodsReceiptRepository;
import com.challengerFinal.arte.repositories.PaymentRepository;
import com.challengerFinal.arte.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentImplements implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    GoodsReceiptRepository goodsReceiptRepository;
    @Override
    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll().stream().map(PaymentDto::new).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> createPayment(PaymentPostDTO paymentPostDTO) {
        if (paymentPostDTO.getName().isEmpty() || paymentPostDTO.getPayments().isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (paymentPostDTO.getName().equals("Debit")){
            if (paymentPostDTO.getPayments().size()>1){
                return new ResponseEntity<>("Debito solo permite 1 pago", HttpStatus.FORBIDDEN);
            }
            if (paymentPostDTO.getPayments().get(0)!=1){
                return new ResponseEntity<>("Debito solo permite 1 pago", HttpStatus.FORBIDDEN);
            }
        }

        if (paymentPostDTO.getName().equals("Cash")){
            if (paymentPostDTO.getPayments().size()>1){
                return new ResponseEntity<>("Cash solo permite 1 pago", HttpStatus.FORBIDDEN);
            }
            if (paymentPostDTO.getPayments().get(0)!=1){
                return new ResponseEntity<>("Cash solo permite 1 pago", HttpStatus.FORBIDDEN);
            }
        }
        if (paymentRepository.findByName(paymentPostDTO.getName()) != null) {
            return new ResponseEntity<>("This type of payment already exists", HttpStatus.FORBIDDEN);
        }
        Payment payment = new Payment(paymentPostDTO.getName(),paymentPostDTO.getPayments());
        paymentRepository.save(payment);

        return new ResponseEntity<>("Payment method created",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updatePayment(PaymentPostDTO paymentPostDTO, Long id) {
        Optional<Payment> paymentAct = paymentRepository.findById(id);
        if (paymentAct == null) {
            return new ResponseEntity<>("This product does not exist", HttpStatus.FORBIDDEN);
        }
        paymentAct.get().setName(paymentPostDTO.getName());
        paymentAct.get().setPayments(paymentPostDTO.getPayments());
        paymentRepository.save(paymentAct.get());

        return new ResponseEntity<>("Updated payment method", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> patchPayment(PaymentPostDTO paymentPostDTO, Long id) {
        Payment paymentAct = paymentRepository.findById(id).orElse(null);

        if(paymentAct == null) {
            return new ResponseEntity<>("Not found", HttpStatus.FORBIDDEN);
        }
        if (paymentPostDTO.getName().equals("Debit")){
            if (paymentPostDTO.getPayments().size()>1){
                return new ResponseEntity<>("Debit only allows 1 payment", HttpStatus.FORBIDDEN);
            }
            if (paymentPostDTO.getPayments().get(0)!=1){
                return new ResponseEntity<>("Debit only allows 1 payment", HttpStatus.FORBIDDEN);
            }
        }
        if (paymentPostDTO.getName().equals("Cash")){

            if (paymentPostDTO.getPayments().size()>1){
                return new ResponseEntity<>("Cash only allows 1 payment", HttpStatus.FORBIDDEN);
            }
            if (paymentPostDTO.getPayments().get(0)!=1){
                return new ResponseEntity<>("Cash only allows 1 payment", HttpStatus.FORBIDDEN);
            }
        }
        if (paymentPostDTO.getName() != null){
            paymentAct.setName(paymentPostDTO.getName());

        }

        if (paymentPostDTO.getPayments() != null) {
            paymentAct.setPayments(paymentPostDTO.getPayments());
        }

        paymentRepository.save(paymentAct);
        return new ResponseEntity<>("Updated payment method"+paymentAct, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> deletePayment(Long id) {
        Payment paymentDelete = paymentRepository.findById(id).orElse(null);
        if (paymentDelete == null) {
            return new ResponseEntity<>("Does not exist", HttpStatus.FORBIDDEN);
        }
        //Primero borrar el asociado:
        Set<GoodsReceipt> invoicesList = paymentDelete.getGoodsReceipts();
        goodsReceiptRepository.deleteAll(invoicesList);
        paymentRepository.deleteById(paymentDelete.getId());

        return new ResponseEntity<>("Payment method deleted", HttpStatus.CREATED);
    }
}
