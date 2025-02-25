package com.aplazo.aplazo_backend_challenge.loans.service;

import com.aplazo.aplazo_backend_challenge.api.loans.dto.LoanDTO;

import java.util.Optional;

public interface GetLoanService {
    Optional<LoanDTO> findById(String loanId);
}
