package com.hu6r1s.order.order.orderline;

import java.util.List;
import java.util.stream.Collectors;
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

  public List<OrderLineResponse> findAllByOrderId(Long orderId) {
    return orderLineRepository.findAllByOrderId(orderId)
        .stream()
        .map(mapper::fromOrderLine)
        .collect(Collectors.toList());
  }
}
