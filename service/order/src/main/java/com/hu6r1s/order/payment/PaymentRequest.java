package com.hu6r1s.order.payment;

import com.hu6r1s.order.customer.dto.response.CustomerResponse;
import com.hu6r1s.order.order.entity.PaymentMethod;
import java.math.BigDecimal;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Long orderId,
    String orderReference,
    CustomerResponse customer
) { }
