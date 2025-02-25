package com.aplazo.aplazo_backend_challenge.customers.service;

import com.aplazo.aplazo_backend_challenge.api.customers.dto.CreateCustomerDTO;
import com.aplazo.aplazo_backend_challenge.api.customers.dto.CustomerDTO;

public interface CreateCustomerService {
    CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO);
}
