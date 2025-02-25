package com.aplazo.aplazo_backend_challenge.api.customers.dto;

import java.time.LocalDate;

public class CreateCustomerDTO {
    private String firstName;
    private String lastName;
    private String secondLastName;
    private LocalDate dateOfBirth;

    public CreateCustomerDTO() {
    }

    public CreateCustomerDTO(String firstName, String lastName, String secondLastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
