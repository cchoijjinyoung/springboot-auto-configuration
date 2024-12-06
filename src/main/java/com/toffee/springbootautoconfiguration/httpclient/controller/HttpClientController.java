package com.toffee.springbootautoconfiguration.httpclient.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HttpClientController {

  private final RestTemplate restTemplate;
  private final RestClient restClient;

  private static final String TARGET_URI = "http://localhost:8080/target";

  // RestTemplate 및 RestClient 로 호출할 target API
  @GetMapping("/target")
  public String target() {
    log.info("target!!");
    return "target";
  }

  @GetMapping("/restTemplate")
  public String restTemplate() {
    ResponseEntity<String> forEntity =
        restTemplate.getForEntity(TARGET_URI, String.class);

    String body = forEntity.getBody();
    log.info("body = {}", body);
    return forEntity.getBody();
  }

  @GetMapping("/restClient")
  public String restClient() {
    String body = restClient.get().uri(TARGET_URI)
        .retrieve()
        .body(String.class);

    log.info("body = {}", body);
    return body;
  }
}
