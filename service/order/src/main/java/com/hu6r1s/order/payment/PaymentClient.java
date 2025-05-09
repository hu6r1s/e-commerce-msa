package com.hu6r1s.order.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "product-service",
    url = "${applicaion.config.payment-url}"
)
public interface PaymentClient {

  @PostMapping
  Long requestOrderPayment(@RequestBody PaymentRequest request);
}
