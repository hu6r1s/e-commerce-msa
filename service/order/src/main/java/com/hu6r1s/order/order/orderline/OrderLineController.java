package com.hu6r1s.order.order.orderline;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orderlines")
@RequiredArgsConstructor
public class OrderLineController {

  private final OrderLineService orderLineService;

  @GetMapping("/order/{orderId}")
  public ResponseEntity<List<OrderLineResponse>> findAllByOrderId(
      @PathVariable Long orderId
  ) {
    return ResponseEntity.ok(orderLineService.findAllByOrderId(orderId));
  }
}
