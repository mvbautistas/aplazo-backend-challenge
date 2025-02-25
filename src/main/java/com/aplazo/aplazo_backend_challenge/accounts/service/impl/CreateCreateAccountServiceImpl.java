package com.aplazo.aplazo_backend_challenge.accounts.service.impl;

import com.aplazo.aplazo_backend_challenge.accounts.repository.AccountRepository;
import com.aplazo.aplazo_backend_challenge.accounts.service.CreateAccountService;
import org.springframework.stereotype.Service;

@Service
class CreateCreateAccountServiceImpl implements CreateAccountService {
    private final AccountRepository accountRepository;

    public CreateCreateAccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
