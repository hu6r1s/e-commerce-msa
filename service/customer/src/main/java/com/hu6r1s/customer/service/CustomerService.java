package com.hu6r1s.customer.service;

import static java.lang.String.format;

import com.hu6r1s.customer.dto.request.CustomerRequest;
import com.hu6r1s.customer.dto.response.CustomerResponse;
import com.hu6r1s.customer.entity.Customer;
import com.hu6r1s.customer.exception.CustomerNotFoundException;
import com.hu6r1s.customer.repository.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper mapper;

  @Transactional
  public String createCustomer(CustomerRequest request) {
    Customer customer = customerRepository.save(mapper.toCustomer(request));
    return customer.getId();
  }

  @Transactional
  public void updateCustomer(CustomerRequest request) {
    Customer customer = customerRepository.findById(request.id())
        .orElseThrow(() -> new CustomerNotFoundException(
            format("Cannot update customer:: No customer found with the provided ID:: %s", request.id())
        ));
    customer.update(request.name(), request.email(), request.address());
  }

  public List<CustomerResponse> findAllCustomers() {
    return customerRepository.findAll()
        .stream()
        .map(mapper::fromCustomer)
        .collect(Collectors.toList());
  }

  public Boolean existsById(String customerId) {
    return customerRepository.findById(customerId)
        .isPresent();
  }

  public CustomerResponse findById(String customerId) {
    return customerRepository.findById(customerId)
        .map(mapper::fromCustomer)
        .orElseThrow(() -> new CustomerNotFoundException(format("No customer found with the provided ID:: %s", customerId)));
  }

  public void delete(String customerId) {
    customerRepository.deleteById(customerId);
  }
}
