package com.toffee.springbootautoconfiguration.objectmapper.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

// 키워드: 큰 부품보다는 작은 부품을 교체하자!
@Configuration
public class ObjectMapperConfig {

  // 첫번째 : ObjectMapper 클래스 자체 설정을 커스텀하는 것
  // @Bean
  public ObjectMapper objectMapper() { // 전체 설정을 해줄 필요없이, 부품만 교체해줘도 된다.
    ObjectMapper objectMapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(
        DateTimeFormatter.ofPattern("yyyyMMdd hhmmss.SSS"));
    javaTimeModule.addSerializer(LocalDateTime.class, localDateTimeSerializer);
    objectMapper.registerModule(javaTimeModule);
    return objectMapper;
  }

  // 두번째 : ObjectMapperBuilder 의 일부를 커스텀해서 주입하는 것
  // @Bean
  public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
    LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(
        DateTimeFormatter.ofPattern("yyyyMMdd hhmmss.SSS"));
    return builder
        .serializerByType(LocalDateTime.class, localDateTimeSerializer)
        .build();
  }

  // 세번째 : ObjectMapperBuilderCustomizer 를 하나 생성해서 builder 의 custom 조건 추가
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer objectMapperBuilderCustomizer3() {
    return new Jackson2ObjectMapperBuilderCustomizer() {
      @Override
      public String toString() {
        return "3";
      }

      @Override
      public void customize(Jackson2ObjectMapperBuilder builder) {
        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(
            DateTimeFormatter.ofPattern("yyyyMMdd hhmmss.SSS"));

        builder.serializerByType(LocalDateTime.class, localDateTimeSerializer);
      }
    };
  }

  // 이 아래부터는 customizerList 에 어떤 순서로 add() 되는가를 알아보기 위함
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer objectMapperBuilderCustomizer1() {
    return new Jackson2ObjectMapperBuilderCustomizer() {
      @Override
      public String toString() {
        return "1";
      }

      @Override
      public void customize(Jackson2ObjectMapperBuilder builder) {
        LocalDateSerializer localDateSerializer = new LocalDateSerializer(
            DateTimeFormatter.ofPattern("yyyyMMdd"));

        builder.serializerByType(LocalDate.class, localDateSerializer);
      }
    };
  }

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer objectMapperBuilderCustomizer2() {
    return new Jackson2ObjectMapperBuilderCustomizer() {
      @Override
      public String toString() {
        return "2";
      }

      @Override
      public void customize(Jackson2ObjectMapperBuilder builder) {
        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(
            DateTimeFormatter.ofPattern("yyyy.MM.dd hhmmss.SSS"));

        LocalDateSerializer localDateSerializer = new LocalDateSerializer(
            DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        builder
            .serializerByType(LocalDateTime.class, localDateTimeSerializer)
            .serializerByType(LocalDate.class, localDateSerializer);
      }
    };
  }
}
