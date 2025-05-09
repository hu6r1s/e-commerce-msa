package com.hu6r1s.notification.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerName,
    String customerEmail
) { }
