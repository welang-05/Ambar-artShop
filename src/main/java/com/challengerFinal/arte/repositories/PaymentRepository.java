package com.challengerFinal.arte.repositories;

import com.challengerFinal.arte.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByName(String typePayment);
}