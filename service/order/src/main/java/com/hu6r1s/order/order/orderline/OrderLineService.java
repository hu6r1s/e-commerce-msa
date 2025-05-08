package com.hu6r1s.order.order.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderLineService {

  private final OrderLineRepository orderLineRepository;
  private final OrderLineMapper mapper;

  @Transactional
  public Long saveOrderLine(OrderLineRequest request) {
    OrderLine order = mapper.toOrderLine(request);
    return orderLineRepository.save(order).getId();
  }
}
