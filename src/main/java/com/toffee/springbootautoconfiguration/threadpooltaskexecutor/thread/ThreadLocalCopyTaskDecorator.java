package com.toffee.springbootautoconfiguration.threadpooltaskexecutor.thread;

import java.util.Map;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

public class ThreadLocalCopyTaskDecorator implements TaskDecorator {

  @Override
  public Runnable decorate(Runnable runnable) {
    Map<String, String> map = MDC.getCopyOfContextMap();

    return new Runnable() {
      @Override
      public void run() {
        if(map != null) MDC.setContextMap(map);
        try{
          runnable.run();
        } finally {
          // 스레드는 계속 재사용 -> 잔여 데이터 초기화로 통해 문제 발생 방지
          // (ex. 시큐리티 컨텍스트 - 다른 회원의 요청임에도 불구하고 스레드 로컬의 데이터를 재사용하면 문제가 발생할 수 있다.)
          MDC.clear();
        }
      }
    };
  }
}
