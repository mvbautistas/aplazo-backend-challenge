package com.aplazo.aplazo_backend_challenge.customers.service.impl;

import com.aplazo.aplazo_backend_challenge.accounts.model.Account;
import com.aplazo.aplazo_backend_challenge.accounts.repository.AccountRepository;
import com.aplazo.aplazo_backend_challenge.api.customers.dto.CreateCustomerDTO;
import com.aplazo.aplazo_backend_challenge.api.customers.dto.CustomerDTO;
import com.aplazo.aplazo_backend_challenge.customers.model.Customer;
import com.aplazo.aplazo_backend_challenge.customers.model.CustomerConverter;
import com.aplazo.aplazo_backend_challenge.customers.repository.CustomerRepository;
import com.aplazo.aplazo_backend_challenge.customers.service.CreateCustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
class CreateCustomerServiceImpl implements CreateCustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    CreateCustomerServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        var createdCustomerAccount = createAccount(createCustomerDTO);
        var customerToCreate = buildCustomerToCreate(createCustomerDTO, createdCustomerAccount);
        var createdCustomer = customerRepository.save(customerToCreate);
        return new CustomerDTO(
                createdCustomer.getId(),
                createdCustomer.getCreatedAt(),
                createdCustomerAccount.getCreditLineAmount(),
                createdCustomerAccount.getAvailableCreditLineAmount()
        );
    }

    private static Customer buildCustomerToCreate(CreateCustomerDTO createCustomerDTO, Account createdCustomerAccount) {
        var customerToCreate = CustomerConverter.fromRequest(createCustomerDTO);
        customerToCreate.setId(UUID.randomUUID().toString());
        customerToCreate.setCreatedAt(LocalDateTime.now());
        customerToCreate.setAccount(createdCustomerAccount);
        return customerToCreate;
    }

    private Account createAccount(CreateCustomerDTO createCustomerDTO) {
        var creditLine = getCreditLine(createCustomerDTO.getDateOfBirth());
        var accountToSave = new Account();
        accountToSave.setAccountId(UUID.randomUUID().toString());
        accountToSave.setCreditLineAmount(creditLine);
        accountToSave.setAvailableCreditLineAmount(creditLine);
        return accountRepository.save(accountToSave);
    }

    public static BigDecimal getCreditLine(LocalDate customerDateOfBirth) {
        long customerAge = ChronoUnit.YEARS.between(customerDateOfBirth, LocalDate.now());
        if (customerAge >= 18 && customerAge <= 25) {
            return new BigDecimal("3000");
        } else if (customerAge >= 26 && customerAge <= 30) {
            return new BigDecimal("5000");
        } else if (customerAge >= 31 && customerAge <= 65) {
            return new BigDecimal("8000");
        }
        return BigDecimal.ZERO;
    }
}
