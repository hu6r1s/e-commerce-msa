package com.hu6r1s.customer.dto.request;

import com.hu6r1s.customer.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
    String id,

    @NotNull(message = "Customer name is required")
    String name,

    @NotNull(message = "Customer name is required")
    @Email(message = "Customer email is not a valid email address")
    String email,

    Address address
) { }
