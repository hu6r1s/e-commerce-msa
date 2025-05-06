package com.hu6r1s.customer.service;

import com.hu6r1s.customer.dto.request.CustomerRequest;
import com.hu6r1s.customer.dto.response.CustomerResponse;
import com.hu6r1s.customer.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

  public Customer toCustomer(CustomerRequest request) {
    if (request == null) {
      return null;
    }
    return Customer.builder()
        .id(request.id())
        .name(request.name())
        .address(request.address())
        .email(request.email())
        .build();
  }

  public CustomerResponse fromCustomer(Customer customer) {
    return new CustomerResponse(
        customer.getId(),
        customer.getName(),
        customer.getEmail(),
        customer.getAddress()
    );
  }
}
