package com.challengerFinal.arte.service;

import com.challengerFinal.arte.dtos.GoodsReceiptDto;
import com.challengerFinal.arte.model.GoodsReceipt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface GoodsReceiptService {
    List<GoodsReceiptDto> getGoodsReceiptsAll();
    GoodsReceipt getGoodsReceiptId(Long id);
    GoodsReceipt saveGoodsReceipt(GoodsReceipt receipt);
    ResponseEntity<Object> createGoodsReceipt(
            @RequestParam int payment,
            @RequestParam String typePayment,
            @RequestParam String numCard,
            @RequestParam LocalDate fechaVen,
            @RequestParam String cvv,
            Authentication authentication);
}
