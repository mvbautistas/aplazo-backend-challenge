package com.aplazo.aplazo_backend_challenge.customers.controller;

import com.aplazo.aplazo_backend_challenge.api.customers.dto.CreateCustomerDTO;
import com.aplazo.aplazo_backend_challenge.api.customers.dto.CustomerDTO;
import com.aplazo.aplazo_backend_challenge.customers.service.CreateCustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/customers")
public class CreateCustomerController {
    private final CreateCustomerService createCustomerService;

    public CreateCustomerController(CreateCustomerService createCustomerService) {
        this.createCustomerService = createCustomerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
        var createdCustomer = createCustomerService.createCustomer(createCustomerDTO);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCustomer.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdCustomer);
    }
}
