package com.aplazo.aplazo_backend_challenge.loans.controller;

import com.aplazo.aplazo_backend_challenge.api.loans.dto.CreateLoanDTO;
import com.aplazo.aplazo_backend_challenge.api.loans.dto.LoanDTO;
import com.aplazo.aplazo_backend_challenge.loans.service.CreateLoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/loans")
public class CreateLoanController {
    private final CreateLoanService createLoanService;

    public CreateLoanController(CreateLoanService createLoanService) {
        this.createLoanService = createLoanService;
    }

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@RequestBody CreateLoanDTO createLoanDTO) {
        return createLoanService.createLoan(createLoanDTO)
                .map(createdLoan -> {
                    var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(createdLoan.getId())
                            .toUri();
                    return ResponseEntity.created(uri).body(createdLoan);
                }).orElse(ResponseEntity.badRequest().build());
    }

}
