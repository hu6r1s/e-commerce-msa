package com.hu6r1s.order.order.orderline;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

  List<OrderLine> findAllByOrderId(Long orderId);
}
