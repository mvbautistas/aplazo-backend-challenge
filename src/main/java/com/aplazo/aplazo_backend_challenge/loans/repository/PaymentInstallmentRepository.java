package com.aplazo.aplazo_backend_challenge.loans.repository;

import com.aplazo.aplazo_backend_challenge.loans.model.PaymentInstallment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentInstallmentRepository extends CrudRepository<PaymentInstallment, String> {
}
