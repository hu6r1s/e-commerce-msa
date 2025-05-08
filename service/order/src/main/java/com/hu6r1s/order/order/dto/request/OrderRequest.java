package com.hu6r1s.order.order.dto.request;

import com.hu6r1s.order.order.entity.PaymentMethod;
import com.hu6r1s.order.product.dto.request.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
    Long id,
    String reference,

    @Positive(message = "Order amount should be positive")
    BigDecimal amount,

    @NotNull(message = "Payment method should be precised")
    PaymentMethod paymentMethod,

    @NotBlank(message = "Customer should be present")
    String customerId,

    @NotEmpty(message = "You should at least purchase one product")
    List<PurchaseRequest> products
) { }
