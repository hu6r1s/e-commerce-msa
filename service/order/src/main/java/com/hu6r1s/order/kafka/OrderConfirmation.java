package com.hu6r1s.order.kafka;

import com.hu6r1s.order.customer.dto.response.CustomerResponse;
import com.hu6r1s.order.order.entity.PaymentMethod;
import com.hu6r1s.order.product.dto.response.PurchaseResponse;
import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    CustomerResponse customer,
    List<PurchaseResponse> products
) { }
