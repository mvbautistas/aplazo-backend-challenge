package com.aplazo.aplazo_backend_challenge.customers.model;

import com.aplazo.aplazo_backend_challenge.api.customers.dto.CreateCustomerDTO;

public class CustomerConverter {
    private CustomerConverter() {
    }

    public static Customer fromRequest(CreateCustomerDTO dto) {
        final Customer customer = new Customer();
        customer.setDateOfBirth(dto.getDateOfBirth());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setSecondLastName(dto.getSecondLastName());
        return customer;
    }
}
