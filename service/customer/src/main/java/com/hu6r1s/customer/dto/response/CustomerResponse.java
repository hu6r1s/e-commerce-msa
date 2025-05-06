package com.hu6r1s.customer.dto.response;

import com.hu6r1s.customer.entity.Address;

public record CustomerResponse(
    String id,
    String name,
    String email,
    Address address
) { }
