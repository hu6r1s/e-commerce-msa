package com.hu6r1s.order.order.orderline;

public record OrderLineRequest(
    Long id,
    Long orderId,
    Long productId,
    double quantity
) { }
