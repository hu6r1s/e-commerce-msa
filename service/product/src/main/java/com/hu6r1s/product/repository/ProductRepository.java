package com.hu6r1s.product.repository;

import com.hu6r1s.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findAllByIdInOrderById(List<Long> productIds);
}
