package com.aplazo.aplazo_backend_challenge.api.customers.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CustomerDTO {
    private String id;
    private LocalDateTime createdAt;
    private BigDecimal creditLineAmount;
    private BigDecimal availableCreditLineAmount;

    public CustomerDTO() {
    }

    public CustomerDTO(String id, LocalDateTime createdAt, BigDecimal creditLineAmount, BigDecimal availableCreditLineAmount) {
        this.id = id;
        this.createdAt = createdAt;
        this.creditLineAmount = creditLineAmount;
        this.availableCreditLineAmount = availableCreditLineAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getCreditLineAmount() {
        return creditLineAmount;
    }

    public void setCreditLineAmount(BigDecimal creditLineAmount) {
        this.creditLineAmount = creditLineAmount;
    }

    public BigDecimal getAvailableCreditLineAmount() {
        return availableCreditLineAmount;
    }

    public void setAvailableCreditLineAmount(BigDecimal availableCreditLineAmount) {
        this.availableCreditLineAmount = availableCreditLineAmount;
    }
}
