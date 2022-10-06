package com.challengerFinal.arte.controllers;

import com.challengerFinal.arte.dtos.GoodsReceiptDto;
import com.challengerFinal.arte.service.GoodsReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ReceiptController {
    @Autowired
    GoodsReceiptService goodsReceiptService;

   @GetMapping(value = "/goodsReceipts")
    public List<GoodsReceiptDto> getExistAll(){
        return goodsReceiptService.getGoodsReceiptsAll();
    }

    @PostMapping("/pay")
    public ResponseEntity<Object> createInvoices(
            @RequestParam int payments,
            @RequestParam String typePayment,
            @RequestParam String numCard,
            @RequestParam String fechaVen,
            @RequestParam String cvv,
            Authentication authentication
    ) {
        LocalDate thruDate = LocalDate.parse(fechaVen);
        return goodsReceiptService.createGoodsReceipt(payments,
                typePayment,
                numCard,
                thruDate,
                cvv,
                authentication);
    }


}
