package com.hu6r1s.customer.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerNotFoundException extends RuntimeException {

  private final String msg;
}
