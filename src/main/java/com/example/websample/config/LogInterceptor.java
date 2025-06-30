package com.example.websample.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("preHandle Interceptor : "+ Thread.currentThread());
        log.info("preHandle handler : " + handler);

        return true;
    }

    @Override //postHandle은 뷰까지 정의할수 있음
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("postHandle Interceptor : "+ Thread.currentThread());
    }

    @Override //에러 발생시 예외사항을 정의할수 있음
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        log.info("afterCompletion Interceptor : "+ Thread.currentThread());

        if (ex != null) {
            log.error("afterCompletion exception : " + ex.getMessage());
        }
    }
}
