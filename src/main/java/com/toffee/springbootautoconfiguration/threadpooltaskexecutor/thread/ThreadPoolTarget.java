package com.toffee.springbootautoconfiguration.threadpooltaskexecutor.thread;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ThreadPoolTarget {
  @Async
  public void async(){
    log.info("async : {}", MDC.get("userId"));
  }
}
