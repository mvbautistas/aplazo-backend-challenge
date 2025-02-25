package com.aplazo.aplazo_backend_challenge.loans.service.impl;

import com.aplazo.aplazo_backend_challenge.accounts.model.Account;
import com.aplazo.aplazo_backend_challenge.api.loans.dto.CreateLoanDTO;
import com.aplazo.aplazo_backend_challenge.customers.model.Customer;
import com.aplazo.aplazo_backend_challenge.loans.model.Loan;
import com.aplazo.aplazo_backend_challenge.loans.model.PaymentInstallmentStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class CreateLoanServiceImplTest {

    @Test
    void testIsValidLoanToCreate() {
        // Given
        var customer = buildCustomer();
        var createLoanDTO = buildCreateLoanDTO("2600");

        // When
        var validLoanToCreate = CreateLoanServiceImpl.isValidLoanToCreate(customer, createLoanDTO);

        // Then
        assertThat(validLoanToCreate, is(true));
    }

    @Test
    void testIsNotValidLoanToCreate() {
        // Given
        var customer = buildCustomer();
        var createLoanDTO = buildCreateLoanDTO("2700");

        // When
        var validLoanToCreate = CreateLoanServiceImpl.isValidLoanToCreate(customer, createLoanDTO);

        // Then
        assertThat(validLoanToCreate, is(false));
    }

    private static CreateLoanDTO buildCreateLoanDTO(String amount) {
        var createLoanDTO = new CreateLoanDTO();
        createLoanDTO.setAmount(new BigDecimal(amount));
        return createLoanDTO;
    }

    @Test
    void testCalculateLoanPaymentInstallments() {
        // Given
        var customer = buildCustomer();
        var loan = buildLoan();

        // When
        var paymentInstallments = CreateLoanServiceImpl.calculateLoanPaymentInstallments(customer, loan);

        // Then
        var paymentDates = Stream.iterate(LocalDate.now(), it -> it.plus(Period.ofWeeks(2)))
                .limit(5)
                .toList();
        IntStream.range(0, paymentInstallments.size()).forEach(i -> {
            var installment = paymentInstallments.get(i);
            assertThat(installment.getId(), is(notNullValue()));
            assertThat(installment.getLoan(), is(loan));
            assertThat(installment.getAmount(), is(new BigDecimal("113.00")));
            assertThat(installment.getScheduledPaymentDate(), is(paymentDates.get(i)));
            assertThat(installment.getStatus(), is(PaymentInstallmentStatus.NEXT));
        });
    }

    private static Customer buildCustomer() {
        var customer = new Customer();
        var account = new Account();
        account.setAvailableCreditLineAmount(new BigDecimal("3000"));
        customer.setFirstName("Coco");
        customer.setAccount(account);
        return customer;
    }

    private static Loan buildLoan() {
        var loan = new Loan();
        loan.setId("loanId");
        loan.setAmount(new BigDecimal("500"));
        return loan;
    }
}