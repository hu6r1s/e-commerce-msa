package com.hu6r1s.product.dto.response;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
  Long productId,
  String name,
  String description,
  BigDecimal price,
  double quantity
) { }
