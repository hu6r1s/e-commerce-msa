package com.hu6r1s.order.product;

import com.hu6r1s.order.customer.exception.BusinessException;
import com.hu6r1s.order.product.dto.request.PurchaseRequest;
import com.hu6r1s.order.product.dto.response.PurchaseResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProductClient {

  @Value("${applicaion.config.product-url}")
  private String productUrl;
  private final RestTemplate restTemplate;

  public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

    HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
    ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {};
    ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
        productUrl + "purchase",
        HttpMethod.POST,
        requestEntity,
        responseType
    );
    if (responseEntity.getStatusCode().isError()) {
      throw new BusinessException("An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
    }
    return responseEntity.getBody();
  }
}
