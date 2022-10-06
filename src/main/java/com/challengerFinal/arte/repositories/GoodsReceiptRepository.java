package com.challengerFinal.arte.repositories;

import com.challengerFinal.arte.model.GoodsReceipt;
import com.challengerFinal.arte.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GoodsReceiptRepository extends JpaRepository<GoodsReceipt, Long> {
    GoodsReceipt findByShoppingCart(ShoppingCart shoppingCart);
}