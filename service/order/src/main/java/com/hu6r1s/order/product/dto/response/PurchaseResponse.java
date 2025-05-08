package com.hu6r1s.order.product.dto.response;

import java.math.BigDecimal;

public record PurchaseResponse(
    Long productId,
    String name,
    String description,
    BigDecimal price,
    double quantity
) { }
