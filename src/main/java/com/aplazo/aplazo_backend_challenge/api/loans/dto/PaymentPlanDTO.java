package com.aplazo.aplazo_backend_challenge.api.loans.dto;

import java.math.BigDecimal;
import java.util.List;

public class PaymentPlanDTO {
    private BigDecimal commissionAmount;
    private List<InstallmentDTO> instalments;

    public PaymentPlanDTO() {
    }

    public PaymentPlanDTO(BigDecimal commissionAmount, List<InstallmentDTO> instalments) {
        this.commissionAmount = commissionAmount;
        this.instalments = instalments;
    }

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public List<InstallmentDTO> getInstalments() {
        return instalments;
    }

    public void setInstalments(List<InstallmentDTO> instalments) {
        this.instalments = instalments;
    }
}
