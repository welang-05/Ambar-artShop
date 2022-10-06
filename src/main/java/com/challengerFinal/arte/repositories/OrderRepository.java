package com.challengerFinal.arte.repositories;

import com.challengerFinal.arte.model.OrderRequest;
import com.challengerFinal.arte.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<OrderRequest, Long> {
    List <OrderRequest> findByShoppingCart(ShoppingCart shoppingCart);
}