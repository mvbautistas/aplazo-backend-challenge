package com.aplazo.aplazo_backend_challenge.loans.service.impl;

import com.aplazo.aplazo_backend_challenge.api.loans.dto.LoanDTO;
import com.aplazo.aplazo_backend_challenge.loans.repository.LoanRepository;
import com.aplazo.aplazo_backend_challenge.loans.service.GetLoanService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class GetLoanServiceImpl implements GetLoanService {
    private final LoanRepository loanRepository;

    public GetLoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Optional<LoanDTO> findById(String loanId) {
        return loanRepository.findById(loanId).map(loan -> {
            var customer = loan.getCustomer();
            var paymentInstallments = loan.getPaymentInstallments();
            return CreateLoanServiceImpl.buildLoanDTO(customer, loan, paymentInstallments);
        });
    }
}
