package com.hu6r1s.customer.controller;

import com.hu6r1s.customer.dto.request.CustomerRequest;
import com.hu6r1s.customer.dto.response.CustomerResponse;
import com.hu6r1s.customer.service.CustomerService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  public ResponseEntity<String> createCustomer(
      @RequestBody @Valid CustomerRequest request
  ) {
    return ResponseEntity.ok(customerService.createCustomer(request));
  }

  @PutMapping
  public ResponseEntity<Void> updateCustomer(
      @RequestBody @Valid CustomerRequest request
  ) {
    customerService.updateCustomer(request);
    return ResponseEntity.accepted().build();
  }

  @GetMapping
  public ResponseEntity<List<CustomerResponse>> findAll() {
    return ResponseEntity.ok(customerService.findAllCustomers());
  }

  @GetMapping("/exists/{customerId}")
  public ResponseEntity<Boolean> existsById(
      @PathVariable String customerId
  ) {
    return ResponseEntity.ok(customerService.existsById(customerId));
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerResponse> findById(
      @PathVariable String customerId
  ) {
    return ResponseEntity.ok(customerService.findById(customerId));
  }

  @DeleteMapping("/{customerId}")
  public ResponseEntity<Void> delete(
      @PathVariable String customerId
  ) {
    customerService.delete(customerId);
    return ResponseEntity.accepted().build();
  }
}
