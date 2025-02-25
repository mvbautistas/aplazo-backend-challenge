package com.aplazo.aplazo_backend_challenge.customers.service.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CreateCustomerServiceImplTest {

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void testGetCreditLine(LocalDate dateOfBirth, BigDecimal expectedCreditLine) {
        var creditLimit = CreateCustomerServiceImpl.getCreditLine(dateOfBirth);

        assertThat(creditLimit, is(expectedCreditLine));
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of(LocalDate.of(2005, 10, 28), new BigDecimal("3000")),
                Arguments.of(LocalDate.of(1997, 7, 19), new BigDecimal("5000")),
                Arguments.of(LocalDate.of(1984, 2, 14), new BigDecimal("8000")),
                Arguments.of(LocalDate.of(2016, 10, 24), BigDecimal.ZERO),
                Arguments.of(LocalDate.of(1954, 1, 1), BigDecimal.ZERO)
        );
    }

}