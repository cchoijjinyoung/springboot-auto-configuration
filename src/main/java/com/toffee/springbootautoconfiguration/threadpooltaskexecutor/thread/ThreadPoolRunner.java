// 간단 테스트 - spring boot 시작할 때 run 실행

package com.toffee.springbootautoconfiguration.threadpooltaskexecutor.thread;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ThreadPoolRunner implements ApplicationRunner {
  private final ThreadPoolTarget target;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info("run..");
    target.async();
  }
}
