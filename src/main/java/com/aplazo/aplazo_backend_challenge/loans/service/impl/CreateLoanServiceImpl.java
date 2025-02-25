package com.aplazo.aplazo_backend_challenge.loans.service.impl;

import com.aplazo.aplazo_backend_challenge.accounts.repository.AccountRepository;
import com.aplazo.aplazo_backend_challenge.api.loans.dto.CreateLoanDTO;
import com.aplazo.aplazo_backend_challenge.api.loans.dto.InstallmentDTO;
import com.aplazo.aplazo_backend_challenge.api.loans.dto.LoanDTO;
import com.aplazo.aplazo_backend_challenge.api.loans.dto.PaymentPlanDTO;
import com.aplazo.aplazo_backend_challenge.customers.model.Customer;
import com.aplazo.aplazo_backend_challenge.customers.repository.CustomerRepository;
import com.aplazo.aplazo_backend_challenge.loans.model.Loan;
import com.aplazo.aplazo_backend_challenge.loans.model.LoanStatus;
import com.aplazo.aplazo_backend_challenge.loans.model.PaymentInstallment;
import com.aplazo.aplazo_backend_challenge.loans.model.PaymentInstallmentStatus;
import com.aplazo.aplazo_backend_challenge.loans.repository.LoanRepository;
import com.aplazo.aplazo_backend_challenge.loans.repository.PaymentInstallmentRepository;
import com.aplazo.aplazo_backend_challenge.loans.service.CreateLoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
class CreateLoanServiceImpl implements CreateLoanService {
    private static final Set<Character> SCHEME_1 = Set.of('C', 'L', 'H');
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateLoanServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final LoanRepository loanRepository;
    private final PaymentInstallmentRepository paymentInstallmentRepository;
    private final AccountRepository accountRepository;

    public CreateLoanServiceImpl(
            CustomerRepository customerRepository,
            LoanRepository loanRepository,
            PaymentInstallmentRepository paymentInstallmentRepository,
            AccountRepository accountRepository
    ) {
        this.customerRepository = customerRepository;
        this.loanRepository = loanRepository;
        this.paymentInstallmentRepository = paymentInstallmentRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public Optional<LoanDTO> createLoan(CreateLoanDTO createLoanDTO) {
        LOGGER.debug("Creating loan: {}", createLoanDTO);
        return customerRepository.findById(createLoanDTO.getCustomerId())
                .filter(customer -> isValidLoanToCreate(customer, createLoanDTO))
                .map(customer -> processLoanCreation(createLoanDTO, customer));
    }

    private LoanDTO processLoanCreation(CreateLoanDTO createLoanDTO, Customer customer) {
        var createdLoan = createCustomerLoan(createLoanDTO, customer);
        var createdPaymentInstallments = createLoanPaymentInstallment(customer, createdLoan);
        updateCustomerAccountAvailableCreditLine(customer, createdLoan);
        return buildLoanDTO(customer, createdLoan, createdPaymentInstallments);
    }

    private Iterable<PaymentInstallment> createLoanPaymentInstallment(Customer customer, Loan createdLoan) {
        var loanPaymentInstallments = calculateLoanPaymentInstallments(customer, createdLoan);
        return paymentInstallmentRepository.saveAll(loanPaymentInstallments);
    }

    public static boolean isValidLoanToCreate(Customer customer, CreateLoanDTO createLoanDTO) {
        var schemePlan = findSchemeplan(customer);
        var commissionAmount = calculateCommissionAmount(createLoanDTO, schemePlan);
        var totalLoanAmount = createLoanDTO.getAmount().add(commissionAmount);
        return customer.getAccount().getAvailableCreditLineAmount().compareTo(totalLoanAmount) >= 0;
    }

    private void updateCustomerAccountAvailableCreditLine(Customer customer, Loan createdLoan) {
        var customerAccount = customer.getAccount();
        customerAccount.setAvailableCreditLineAmount(
                customerAccount.getAvailableCreditLineAmount()
                        .subtract(createdLoan.getAmount())
                        .subtract(createdLoan.getCommissionAmount()));
        accountRepository.save(customerAccount);
    }

    public static LoanDTO buildLoanDTO(
            Customer customer,
            Loan createdLoan,
            Iterable<PaymentInstallment> createdPaymentInstallments
    ) {
        var loan = new LoanDTO();
        loan.setId(createdLoan.getId());
        loan.setCustomerId(customer.getId());
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setCreatedAt(createdLoan.getCreatedAt());
        var paymentPlan = new PaymentPlanDTO();
        paymentPlan.setCommissionAmount(createdLoan.getCommissionAmount());
        var paymentInstallmentsDTOs = StreamSupport.stream(createdPaymentInstallments.spliterator(), false)
                .map(installment -> new InstallmentDTO(
                        installment.getAmount(),
                        installment.getScheduledPaymentDate(),
                        installment.getStatus()))
                .toList();
        paymentPlan.setInstalments(paymentInstallmentsDTOs);
        loan.setPaymentPlan(paymentPlan);
        return loan;
    }

    private Loan createCustomerLoan(CreateLoanDTO createLoanDTO, Customer customer) {
        var schemePlan = findSchemeplan(customer);
        var loanToCreate = new Loan();
        loanToCreate.setId(UUID.randomUUID().toString());
        loanToCreate.setCustomer(customer);
        loanToCreate.setStatus(LoanStatus.ACTIVE);
        loanToCreate.setCreatedAt(LocalDateTime.now());
        loanToCreate.setInterestRate(schemePlan.interestRate());
        loanToCreate.setCommissionAmount(calculateCommissionAmount(createLoanDTO, schemePlan));
        loanToCreate.setAmount(createLoanDTO.getAmount());
        return loanRepository.save(loanToCreate);
    }

    private static BigDecimal calculateCommissionAmount(CreateLoanDTO createLoanDTO, SchemePlan schemePlan) {
        return createLoanDTO.getAmount()
                .multiply(schemePlan.interestRate()
                        .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
    }

    private static SchemePlan findSchemeplan(Customer customer) {
        return SCHEME_1.contains(customer.getFirstName().charAt(0)) ?
                new SchemePlan(new BigDecimal(5), Period.ofWeeks(2), new BigDecimal(13)) :
                new SchemePlan(new BigDecimal(5), Period.ofWeeks(2), new BigDecimal(16));
    }

    public static List<PaymentInstallment> calculateLoanPaymentInstallments(Customer customer, Loan createdLoan) {
        var schemePlan = findSchemeplan(customer);
        var installmentAmount = createdLoan.getAmount()
                .multiply(schemePlan.interestRate()
                        .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP)
                        .add(new BigDecimal("1")))
                .divide(schemePlan.payments(), 2, RoundingMode.HALF_UP);
        var paymentDates = Stream.iterate(LocalDate.now(), it -> it.plus(schemePlan.period()))
                .limit(schemePlan.payments().intValue())
                .toList();
        return IntStream.range(0, schemePlan.payments().intValue()).mapToObj(i -> {
            var paymentInstallment = new PaymentInstallment();
            paymentInstallment.setId(UUID.randomUUID().toString());
            paymentInstallment.setLoan(createdLoan);
            paymentInstallment.setAmount(installmentAmount);
            paymentInstallment.setScheduledPaymentDate(paymentDates.get(i));
            paymentInstallment.setStatus(PaymentInstallmentStatus.NEXT);
            return paymentInstallment;
        }).toList();
    }
}

record SchemePlan(
        BigDecimal payments,
        Period period,
        BigDecimal interestRate
) {
}
