package com.aplazo.aplazo_backend_challenge.customers.service.impl;

import com.aplazo.aplazo_backend_challenge.api.customers.dto.CustomerDTO;
import com.aplazo.aplazo_backend_challenge.customers.repository.CustomerRepository;
import com.aplazo.aplazo_backend_challenge.customers.service.GetCustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class GetCustomerServiceImpl implements GetCustomerService {
    private final CustomerRepository customerRepository;

    public GetCustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customer -> new CustomerDTO(
                        customer.getId(),
                        customer.getCreatedAt(),
                        customer.getAccount().getCreditLineAmount(),
                        customer.getAccount().getAvailableCreditLineAmount()));
    }
}
