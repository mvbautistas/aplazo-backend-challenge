package com.aplazo.aplazo_backend_challenge.loans.controller;

import com.aplazo.aplazo_backend_challenge.api.loans.dto.LoanDTO;
import com.aplazo.aplazo_backend_challenge.loans.service.GetLoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class GetLoanController {
    private final GetLoanService getLoanService;

    public GetLoanController(GetLoanService getLoanService) {
        this.getLoanService = getLoanService;
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanDTO> findLoanById(@PathVariable String loanId) {
        return getLoanService.findById(loanId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
