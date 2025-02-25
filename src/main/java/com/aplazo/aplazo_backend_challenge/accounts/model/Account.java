package com.aplazo.aplazo_backend_challenge.accounts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private String accountId;
    private BigDecimal creditLineAmount;
    private BigDecimal availableCreditLineAmount;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
