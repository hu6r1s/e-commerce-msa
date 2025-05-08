package com.hu6r1s.order.order.service;

import com.hu6r1s.order.customer.CustomerClient;
import com.hu6r1s.order.customer.dto.response.CustomerResponse;
import com.hu6r1s.order.customer.exception.BusinessException;
import com.hu6r1s.order.order.dto.request.OrderRequest;
import com.hu6r1s.order.order.entity.Order;
import com.hu6r1s.order.order.orderline.OrderLineRequest;
import com.hu6r1s.order.order.orderline.OrderLineService;
import com.hu6r1s.order.order.repository.OrderRepository;
import com.hu6r1s.order.product.ProductClient;
import com.hu6r1s.order.product.dto.request.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final CustomerClient customerClient;
  private final ProductClient productClient;
  private final OrderMapper mapper;
  private final OrderLineService orderLineService;

  public Long createOrder(OrderRequest request) {
    CustomerResponse customer = customerClient.findCustomerById(request.customerId())
        .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exists with the provided"));

    productClient.purchaseProducts(request.products());

    Order order = orderRepository.save(mapper.toOrder(request));

    for (PurchaseRequest purchaseRequest: request.products()) {
      orderLineService.saveOrderLine(
          new OrderLineRequest(
              null,
              order.getId(),
              purchaseRequest.productId(),
              purchaseRequest.quantity()
          )
      );
    }

    // todo start payment process
    return null;
  }
}
