package com.hu6r1s.order.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

  private final String msg;
}
