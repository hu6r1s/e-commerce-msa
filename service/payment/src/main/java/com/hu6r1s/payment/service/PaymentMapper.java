package com.hu6r1s.payment.service;

import com.hu6r1s.payment.dto.request.PaymentRequest;
import com.hu6r1s.payment.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

  public Payment toPayment(PaymentRequest request) {
    return Payment.builder()
        .id(request.id())
        .paymentMethod(request.paymentMethod())
        .amount(request.amount())
        .orderId(request.orderId())
        .build();
  }
}
