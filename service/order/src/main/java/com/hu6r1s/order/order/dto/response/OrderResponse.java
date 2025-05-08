package com.hu6r1s.order.order.dto.response;

import com.hu6r1s.order.order.entity.PaymentMethod;
import java.math.BigDecimal;

public record OrderResponse(
  Long id,
  String reference,
  BigDecimal amount,
  PaymentMethod paymentMethod,
  String customerId
) { }
