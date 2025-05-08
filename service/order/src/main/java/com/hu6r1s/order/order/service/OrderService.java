package com.hu6r1s.order.order.service;

import com.hu6r1s.order.customer.CustomerClient;
import com.hu6r1s.order.customer.dto.response.CustomerResponse;
import com.hu6r1s.order.customer.exception.BusinessException;
import com.hu6r1s.order.kafka.OrderConfirmation;
import com.hu6r1s.order.kafka.OrderProducer;
import com.hu6r1s.order.order.dto.request.OrderRequest;
import com.hu6r1s.order.order.dto.response.OrderResponse;
import com.hu6r1s.order.order.entity.Order;
import com.hu6r1s.order.order.orderline.OrderLineRequest;
import com.hu6r1s.order.order.orderline.OrderLineService;
import com.hu6r1s.order.order.repository.OrderRepository;
import com.hu6r1s.order.product.ProductClient;
import com.hu6r1s.order.product.dto.request.PurchaseRequest;
import com.hu6r1s.order.product.dto.response.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
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
  private final OrderProducer orderProducer;

  public Long createOrder(OrderRequest request) {
    CustomerResponse customer = customerClient.findCustomerById(request.customerId())
        .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exists with the provided"));

    List<PurchaseResponse> purchasedOProducts = productClient.purchaseProducts(request.products());

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

    orderProducer.sendOrderConfirmation(
        new OrderConfirmation(
            request.reference(),
            request.amount(),
            request.paymentMethod(),
            customer,
            purchasedOProducts
        )
    );
    return order.getId();
  }

  public List<OrderResponse> findAll() {
    return orderRepository.findAll()
        .stream()
        .map(mapper::fromOrder)
        .collect(Collectors.toList());
  }

  public OrderResponse findById(Long orderId) {
    return orderRepository.findById(orderId)
        .map(mapper::fromOrder)
        .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));
  }
}
