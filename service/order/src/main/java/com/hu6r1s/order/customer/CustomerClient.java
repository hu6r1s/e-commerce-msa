package com.hu6r1s.order.customer;

import com.hu6r1s.order.customer.dto.response.CustomerResponse;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "customer-service",
    url = "${application.config.customer-url}"
)
public interface CustomerClient {

  @GetMapping("/{customerId}")
  Optional<CustomerResponse> findCustomerById(@PathVariable String customerId);
}
