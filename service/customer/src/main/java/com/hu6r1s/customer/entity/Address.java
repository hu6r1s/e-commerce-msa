package com.hu6r1s.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Address {

  private String street;
  private String houseNumber;
  private String zipCode;
}
