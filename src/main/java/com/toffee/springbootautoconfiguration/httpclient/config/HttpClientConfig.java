package com.toffee.springbootautoconfiguration.httpclient.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate 와 RestClient 커스텀 하고 싶을 때, 어떻게 하는게 좋을까?
 */
@Configuration
public class HttpClientConfig {

  // 첫번째 : 통째로 등록
  // @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    // restTemplate.set..(); // 번거롭다!
    return new RestTemplate();
  }

  // 두번째 : 스프링에서 자동으로 빈으로 등록해주는 RestTemplateBuilder 를 주입받아서 등록
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder
        // 내가 원하는 setting 필요
        .build();
  }

  // @Bean
  public RestClient restClient() {
    return RestClient.create();
  }

  @Bean
  public RestClient restClient(RestClient.Builder builder) {
    return builder
        // 내가 원하는 setting 필요
        .build();
  }
}
