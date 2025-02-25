package com.aplazo.aplazo_backend_challenge.api.loans.dto;

import java.math.BigDecimal;

public class CreateLoanDTO {
    private String customerId;
    private BigDecimal amount;

    public CreateLoanDTO() {
    }

    public CreateLoanDTO(String customerId, BigDecimal amount) {
        this.customerId = customerId;
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CreateLoanDTO{" +
                "customerId='" + customerId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
