package com.aplazo.aplazo_backend_challenge.customers.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
    @GetMapping
    public String sayHello() {
        return "Hey!";
    }
}
