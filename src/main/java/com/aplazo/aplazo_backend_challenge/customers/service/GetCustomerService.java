package com.aplazo.aplazo_backend_challenge.customers.service;

import com.aplazo.aplazo_backend_challenge.api.customers.dto.CustomerDTO;

import java.util.Optional;

public interface GetCustomerService {
    Optional<CustomerDTO> getCustomerById(String customerId);
}
