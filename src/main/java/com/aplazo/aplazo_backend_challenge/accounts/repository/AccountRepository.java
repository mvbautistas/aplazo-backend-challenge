package com.aplazo.aplazo_backend_challenge.accounts.repository;

import com.aplazo.aplazo_backend_challenge.accounts.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
}
