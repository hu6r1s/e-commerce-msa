package com.hu6r1s.order.order.orderline;

import com.hu6r1s.order.order.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

  public OrderLine toOrderLine(OrderLineRequest request) {
    return OrderLine.builder()
        .id(request.id())
        .quantity(request.quantity())
        .order(
            Order.builder()
                .id(request.orderId())
                .build()
        )
        .productId(request.productId())
        .build();
  }

  public OrderLineResponse fromOrderLine(OrderLine orderLine) {
    return new OrderLineResponse(
        orderLine.getId(), orderLine.getQuantity()
    );
  }

}
