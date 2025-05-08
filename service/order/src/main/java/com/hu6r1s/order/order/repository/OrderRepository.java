package com.hu6r1s.order.order.repository;

import com.hu6r1s.order.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
