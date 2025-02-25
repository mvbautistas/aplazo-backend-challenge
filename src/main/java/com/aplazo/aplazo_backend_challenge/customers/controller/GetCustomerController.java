package com.aplazo.aplazo_backend_challenge.customers.controller;

import com.aplazo.aplazo_backend_challenge.api.customers.dto.CustomerDTO;
import com.aplazo.aplazo_backend_challenge.customers.service.GetCustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class GetCustomerController {
    private final GetCustomerService getCustomerService;

    public GetCustomerController(GetCustomerService getCustomerService) {
        this.getCustomerService = getCustomerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String customerId) {
        return getCustomerService.getCustomerById(customerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
