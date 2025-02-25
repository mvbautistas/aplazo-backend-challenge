package com.aplazo.aplazo_backend_challenge.loans.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payment_installments")
public class PaymentInstallment {
    @Id
    private String id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;
    private BigDecimal amount;
    private LocalDate scheduledPaymentDate;
    @Enumerated(EnumType.STRING)
    private PaymentInstallmentStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
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
