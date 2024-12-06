package com.toffee.springbootautoconfiguration.threadpooltaskexecutor.controller;

import com.toffee.springbootautoconfiguration.threadpooltaskexecutor.thread.ThreadPoolTarget;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ThreadPoolController {
  private final ThreadPoolTarget threadPoolTarget;

  @GetMapping("thread")
  public String thread(){
    MDC.put("userId", "jieun");
    log.info("thread : {}", MDC.get("userId"));
    threadPoolTarget.async();
    return "ok";
  }
}
