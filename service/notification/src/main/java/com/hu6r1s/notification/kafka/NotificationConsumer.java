package com.hu6r1s.notification.kafka;

import com.hu6r1s.notification.email.EmailService;
import com.hu6r1s.notification.kafka.order.OrderConfirmation;
import com.hu6r1s.notification.kafka.payment.PaymentConfirmation;
import com.hu6r1s.notification.notification.entity.Notification;
import com.hu6r1s.notification.notification.entity.NotificationType;
import com.hu6r1s.notification.notification.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumer {

  private final NotificationRepository notificationRepository;
  private final EmailService emailService;

  @KafkaListener(topics = "payment-topic")
  public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation)
      throws MessagingException {
    log.info(String.format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
    notificationRepository.save(
        Notification.builder()
            .type(NotificationType.PAYMENT_CONFIRMATION)
            .notificationDate(LocalDateTime.now())
            .paymentConfirmation(paymentConfirmation)
            .build()
    );

    emailService.sentPaymentSuccessEmail(
        paymentConfirmation.customerEmail(),
        paymentConfirmation.customerName(),
        paymentConfirmation.amount(),
        paymentConfirmation.orderReference()
    );
  }

  @KafkaListener(topics = "order-topic")
  public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation)
      throws MessagingException {
    log.info(String.format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
    notificationRepository.save(
        Notification.builder()
            .type(NotificationType.ORDER_CONFIRMATION)
            .notificationDate(LocalDateTime.now())
            .orderConfirmation(orderConfirmation)
            .build()
    );

    emailService.sendOrderConfirmationEmail(
        orderConfirmation.customer().email(),
        orderConfirmation.customer().name(),
        orderConfirmation.totalAmount(),
        orderConfirmation.orderReference(),
        orderConfirmation.products()
    );
  }
}
