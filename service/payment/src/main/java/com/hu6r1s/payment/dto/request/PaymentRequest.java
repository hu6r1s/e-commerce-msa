package com.hu6r1s.payment.dto.request;

import com.hu6r1s.payment.entity.Customer;
import com.hu6r1s.payment.entity.PaymentMethod;
import java.math.BigDecimal;

public record PaymentRequest(
    Long id,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Long orderId,
    String orderReference,
    Customer customer
) { }
