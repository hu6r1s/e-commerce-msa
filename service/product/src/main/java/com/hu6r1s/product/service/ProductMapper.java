package com.hu6r1s.product.service;

import com.hu6r1s.product.dto.request.ProductRequest;
import com.hu6r1s.product.dto.response.ProductPurchaseResponse;
import com.hu6r1s.product.dto.response.ProductResponse;
import com.hu6r1s.product.entity.Category;
import com.hu6r1s.product.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

  public Product toProduct(ProductRequest request) {
    return Product.builder()
        .id(request.id())
        .name(request.name())
        .description((request.description()))
        .availableQuantity(request.availableQuantity())
        .price(request.price())
        .category(
            Category.builder()
                .id(request.categoryId())
                .build()
        )
        .build();
  }

  public ProductResponse fromProduct(Product product) {
    return new ProductResponse(
        product.getId(),
        product.getName(),
        product.getDescription(),
        product.getAvailableQuantity(),
        product.getPrice(),
        product.getCategory().getId(),
        product.getCategory().getName(),
        product.getCategory().getDescription()
    );
  }

  public ProductPurchaseResponse fromProductPurchase(Product product, double quantity) {
    return new ProductPurchaseResponse(
        product.getId(),
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        quantity
    );
  }
}
