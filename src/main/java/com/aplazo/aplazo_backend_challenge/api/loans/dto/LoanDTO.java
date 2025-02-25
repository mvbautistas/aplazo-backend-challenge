package com.aplazo.aplazo_backend_challenge.api.loans.dto;

import com.aplazo.aplazo_backend_challenge.loans.model.LoanStatus;

import java.time.LocalDateTime;

public class LoanDTO {
    private String id;
    private String customerId;
    private LoanStatus status;
    private LocalDateTime createdAt;
    private PaymentPlanDTO paymentPlan;

    public LoanDTO() {
    }

    public LoanDTO(String id, String customerId, LoanStatus status, LocalDateTime createdAt, PaymentPlanDTO paymentPlan) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.createdAt = createdAt;
        this.paymentPlan = paymentPlan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PaymentPlanDTO getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(PaymentPlanDTO paymentPlan) {
        this.paymentPlan = paymentPlan;
    }
}
