package com.hu6r1s.customer.entity;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @Id
  private String id;
  private String name;
  private String email;
  private Address address;

  public void update(String name, String email, Address address) {
    if (StringUtils.isNotBlank(name)) {
      this.name = name;
    }
    if (StringUtils.isNotBlank(email)) {
      this.email = email;
    }
    if (address != null) {
      this.address = address;
    }
  }
}
