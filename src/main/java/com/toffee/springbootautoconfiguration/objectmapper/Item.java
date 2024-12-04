package com.toffee.springbootautoconfiguration.objectmapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 스프링 부트에서 자동으로 빈으로 등록되는 ObjectMapper 는 LocalDateTime 타입을 수용한다.
 * 해당 클래스는,
 * 자동으로 빈으로 등록되는 ObjectMapper 와 우리가 직접 빈으로 등록하는 ObjectMapper 둘 모두
 * 잘 동작되는 지 확인하기 위한 클래스이다.
 */
@Data
public class Item {
  private String id;
  private String name;
  private LocalDateTime localDateTime;
  private LocalDate localDate;
}
