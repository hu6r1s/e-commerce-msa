package com.hu6r1s.product.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ProductRequest(
    Long id,

    @NotNull(message = "Product name is required")
    String name,

    @NotNull(message = "Product description is required")
    String description,

    @Positive(message = "Available quantity should be positive")
    double availableQuantity,

    @Positive(message = "Price should be positive")
    BigDecimal price,

    @NotNull(message = "Product category is required")
    Long categoryId
) { }
