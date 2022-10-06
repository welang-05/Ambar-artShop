package com.challengerFinal.arte.dtos;

import com.challengerFinal.arte.model.GoodsReceipt;

import java.io.Serializable;
import java.time.LocalDate;

public class GoodsReceiptDto implements Serializable {
    private Long id;

    public GoodsReceiptDto(GoodsReceipt goodsReceipt) {
        this.id = goodsReceipt.getId();
    }

    public Long getId() {
        return id;
    }
}
