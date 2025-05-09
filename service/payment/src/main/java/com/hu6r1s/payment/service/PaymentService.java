package com.hu6r1s.payment.service;

import com.hu6r1s.payment.dto.request.PaymentRequest;
import com.hu6r1s.payment.entity.Payment;
import com.hu6r1s.payment.notification.NotificationProducer;
import com.hu6r1s.payment.notification.PaymentNotificationRequest;
import com.hu6r1s.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final PaymentRepository paymentRepository;
  private final PaymentMapper mapper;
  private final NotificationProducer notificationProducer;

  public Long createPayment(PaymentRequest request) {
    Payment payment = paymentRepository.save(mapper.toPayment(request));
    notificationProducer.sendNotification(
        new PaymentNotificationRequest(
            request.orderReference(),
            request.amount(),
            request.paymentMethod(),
            request.customer().name(),
            request.customer().email()
        )
    );
    return payment.getId();
  }
}
