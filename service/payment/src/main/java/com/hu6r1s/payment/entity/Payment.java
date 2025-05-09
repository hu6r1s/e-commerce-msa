package com.hu6r1s.payment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
@EntityListeners(AuditingEntityListener.class)
public class Payment {

  @Id
  @GeneratedValue
  private Long id;
  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;
  private Long orderId;

  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime lastModifiedDate;
}
