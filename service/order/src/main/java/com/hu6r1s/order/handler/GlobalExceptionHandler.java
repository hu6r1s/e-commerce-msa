package com.hu6r1s.order.handler;

import com.hu6r1s.order.exception.BusinessException;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<String> handle(BusinessException ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ex.getMsg());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex) {
    HashMap<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors()
        .forEach(error -> {
          String fieldName = ((FieldError) error).getField();
          String errorMessage = error.getDefaultMessage();
          errors.put(fieldName, errorMessage);
        });
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse(errors));
  }
}
