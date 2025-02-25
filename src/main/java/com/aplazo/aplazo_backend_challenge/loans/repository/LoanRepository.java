package com.aplazo.aplazo_backend_challenge.loans.repository;

import com.aplazo.aplazo_backend_challenge.loans.model.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, String> {
}
