package com.hu6r1s.product.service;

import com.hu6r1s.product.dto.request.ProductPurchaseRequest;
import com.hu6r1s.product.dto.request.ProductRequest;
import com.hu6r1s.product.dto.response.ProductPurchaseResponse;
import com.hu6r1s.product.dto.response.ProductResponse;
import com.hu6r1s.product.entity.Product;
import com.hu6r1s.product.exception.ProductPurchaseException;
import com.hu6r1s.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper mapper;

  @Transactional
  public Long createProduct(ProductRequest request) {
    Product product = mapper.toProduct(request);
    return productRepository.save(product).getId();
  }

  @Transactional
  public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
    List<Long> productIds = request.stream()
        .map(ProductPurchaseRequest::productId)
        .toList();
    List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);
    if (productIds.size() != storedProducts.size()) {
      throw new ProductPurchaseException("One or more products does not exits");
    }

    List<ProductPurchaseRequest> storesRequest = request.stream()
        .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
        .toList();
    ArrayList<ProductPurchaseResponse> purchasedProducts = new ArrayList<ProductPurchaseResponse>();
    for (int i = 0; i < storedProducts.size(); i++) {
      Product product = storedProducts.get(i);
      ProductPurchaseRequest productRequest = storesRequest.get(i);
      if (product.getAvailableQuantity() < productRequest.quantity()) {
        throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.quantity());
      }
      var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
      product.updateAvailableQuantity(newAvailableQuantity);
      purchasedProducts.add(mapper.fromProductPurchase(product, productRequest.quantity()));
    }
    return purchasedProducts;
  }

  public ProductResponse findById(Long productId) {
    return productRepository.findById(productId)
        .map(mapper::fromProduct)
        .orElseThrow(() -> new EntityNotFoundException("Product not found with the ID:: " + productId));
  }

  public List<ProductResponse> findAll() {
    return productRepository.findAll()
        .stream()
        .map(mapper::fromProduct)
        .collect(Collectors.toList());
  }
}
