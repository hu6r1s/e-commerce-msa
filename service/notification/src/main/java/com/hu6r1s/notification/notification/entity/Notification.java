package com.hu6r1s.notification.notification.entity;

import com.hu6r1s.notification.kafka.order.OrderConfirmation;
import com.hu6r1s.notification.kafka.payment.PaymentConfirmation;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

  @Id
  private String id;
  private NotificationType type;
  private LocalDateTime notificationDate;
  private OrderConfirmation orderConfirmation;
  private PaymentConfirmation paymentConfirmation;
}
