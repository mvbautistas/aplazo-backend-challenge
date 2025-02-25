package com.aplazo.aplazo_backend_challenge.customers.repository;

import com.aplazo.aplazo_backend_challenge.customers.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
}
