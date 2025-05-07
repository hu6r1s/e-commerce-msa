package com.hu6r1s.product.dto.request;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
    @NotNull(message = "Product is mandatory")
    Long productId,

    @NotNull(message = "Quantity is mandatory")
    double quantity
) { }
