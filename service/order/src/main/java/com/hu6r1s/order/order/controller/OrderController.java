package com.hu6r1s.order.order.controller;

import com.hu6r1s.order.order.dto.request.OrderRequest;
import com.hu6r1s.order.order.dto.response.OrderResponse;
import com.hu6r1s.order.order.service.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<Long> createOrder(
    @RequestBody @Valid OrderRequest request
  ) {
    return ResponseEntity.ok(orderService.createOrder(request));
  }

  @GetMapping
  public ResponseEntity<List<OrderResponse>> findAll() {
    return ResponseEntity.ok(orderService.findAll());
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<OrderResponse> findById(
      @PathVariable Long orderId
  ) {
    return ResponseEntity.ok(orderService.findById(orderId));
  }
}
