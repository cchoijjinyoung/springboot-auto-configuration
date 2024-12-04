package com.toffee.springbootautoconfiguration.objectmapper.controller;

import com.toffee.springbootautoconfiguration.objectmapper.Item;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ObjectMapperController {

  // 과연 Customizer List 에 들어가는 순서는 어떻게 될까?
  // ObjectMapperConfig 에 3-1-2 순으로 빈을 등록해보자!
  // 결과 : -> 3-1-2로 출력됨
  // 배운점 : List 에 윗 코드부터 빈으로 등록된다.
  private final List<Jackson2ObjectMapperBuilderCustomizer> list;

  @GetMapping("/objectMapper")
  public Item getItem() {
    Item item = new Item();
    item.setId("1L");
    item.setName("name");
    item.setLocalDateTime(LocalDateTime.now());
    item.setLocalDate(LocalDate.now());

    for (Jackson2ObjectMapperBuilderCustomizer customizer : list) {
      System.out.println(customizer.toString());
    }
    return item;
  }
}
