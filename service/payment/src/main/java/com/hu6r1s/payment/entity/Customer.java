package com.hu6r1s.payment.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
    String id,

    @NotNull(message = "Name is required")
    String name,

    @NotNull(message = "Email is required")
    @Email(message = "The customer is not correctly formatted")
    String email
) { }
