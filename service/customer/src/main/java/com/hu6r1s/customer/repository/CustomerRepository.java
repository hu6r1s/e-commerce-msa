package com.hu6r1s.customer.repository;

import com.hu6r1s.customer.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
