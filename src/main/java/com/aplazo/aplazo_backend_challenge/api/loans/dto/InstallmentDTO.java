package com.aplazo.aplazo_backend_challenge.api.loans.dto;

import com.aplazo.aplazo_backend_challenge.loans.model.PaymentInstallmentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InstallmentDTO {
    private BigDecimal amount;
    private LocalDate scheduledPaymentDate;
    private PaymentInstallmentStatus status;

    public InstallmentDTO() {
    }

    public InstallmentDTO(BigDecimal amount, LocalDate scheduledPaymentDate, PaymentInstallmentStatus status) {
        this.amount = amount;
        this.scheduledPaymentDate = scheduledPaymentDate;
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getScheduledPaymentDate() {
        return scheduledPaymentDate;
    }

    public void setScheduledPaymentDate(LocalDate scheduledPaymentDate) {
        this.scheduledPaymentDate = scheduledPaymentDate;
    }

    public PaymentInstallmentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentInstallmentStatus status) {
        this.status = status;
    }
}
