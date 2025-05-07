package com.hu6r1s.product.handler;

import com.hu6r1s.product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ProductPurchaseException.class)
  public ResponseEntity<String> handle(ProductPurchaseException ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handle(EntityNotFoundException ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());
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
