package com.hu6r1s.payment.notification;

import com.hu6r1s.payment.entity.PaymentMethod;
import java.math.BigDecimal;

public record PaymentNotificationRequest(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerName,
    String customerEmail
) { }
