/**
 *    1. ThreadPoolTaskExecutor의 ThreadNamePrefix를 변경해보자.
 *    1-1. ThreadPoolTaskExecutor를 직접 등록해서 변경해보자.
 *    1-2. application.yml을 이용해서 변경해보자.
 *
 *    2. 비동기 스레드를 실행시켜 org.slf4j.MDC를 작업하고 비동기 스레드에서 MDC를 사용하면 어떻게 될까?
 *    2-1. ThreadPoolTaskExecutor를 기본 설정으로 실행해보자. -> 비동기라 복사안됨.
 *    2-2. TaskDecorator를 설정을 변경해보자. -> 기존의 스레드에서 내용
 *    2-2-1. ThreadPoolTaskExecutor를 빈으로 등록하며 설정을 변경해보자.
 *    2-2-2. TaskDecorator를 빈으로 등록하여 변경해보자.
 */

package com.toffee.springbootautoconfiguration.threadpooltaskexecutor.config;

import com.toffee.springbootautoconfiguration.threadpooltaskexecutor.thread.ThreadLocalCopyTaskDecorator;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

  /**
   * 1-1. ThreadPoolTaskExecutor를 직접 등록해서 변경해보자.
   * 결과: ThreadNamePrefix를 바꿀 수 있다.
   * 문제: MDC(스레드별 정보)가 비동기 스레드에서는 찾을 수 없다.
   */
  // @Bean
//  public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
//    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//    executor.setThreadNamePrefix("myThread-");
//    return executor;
//  }

  /**
   * 참고: 스프링 부트에서 제공하는 builder로 생성 가능하다.
   */
//  @Bean
//  public ThreadPoolTaskExecutor threadPoolTaskExecutor(ThreadPoolTaskExecutorBuilder builder){
//    return builder.build();
//  }

  /**
   * 2-2-1. ThreadPoolTaskExecutor를 빈으로 등록하며 설정을 변경해보자.
   * 결과: decorator를 설정함으로써 호출된 비동기 스레드가 MDC 정보를 이어서 받을 수 있다.
   */
//  @Bean
//  public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
//    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//    executor.setThreadNamePrefix("myThread!-");
//    executor.setTaskDecorator(new ThreadLocalCopyTaskDecorator());
//    return executor;
//  }

  /**
   * 2-2-2. TaskDecorator를 빈으로 등록하여 변경해보자
   * 결과: application.yml 설정을 적극 활용 가능하면서 TaskDecorator 같은 부품도 교체가 가능하다.
   */
  @Bean
  public TaskDecorator myTestDecorator(){
    return new ThreadLocalCopyTaskDecorator();
  }

  /**
   * 참고! : Decorator는 한개만 사용할 수 있다. 내부적으로 Decorator가 2개 이상이면 에러를 발생시켜 제대로 세팅해주지 않는다.
   * 관련 코드: builder = builder.taskDecorator((TaskDecorator)taskDecorator.getIfUnique());
   */
//  @Bean
//  public TaskDecorator myTestDecorator2(){
//    return new ThreadLocalCopyTaskDecorator();
//  }

}
