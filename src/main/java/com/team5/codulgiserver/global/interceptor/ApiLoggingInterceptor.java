package com.team5.codulgiserver.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@Slf4j
public class ApiLoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /* 인터셉터 진입 시점 */
        long startTime = System.currentTimeMillis();
        // long을 Instant로 변환
        Instant instant = Instant.ofEpochMilli(startTime);
        // Instant를 LocalDateTime으로 변환 (시스템 기본 시간대)
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());

        request.setAttribute("startTime", dateTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

        long t_endTime = System.currentTimeMillis();
        // long을 Instant로 변환
        Instant instant = Instant.ofEpochMilli(t_endTime);
        // Instant를 LocalDateTime으로 변환 (시스템 기본 시간대)
        LocalDateTime endTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());

        LocalDateTime initialTime = (LocalDateTime) request.getAttribute("initialTime");
        LocalDateTime startTime = (LocalDateTime) request.getAttribute("startTime");

        String line = initialTime + "," + startTime + "," + endTime;

        System.out.println(line);
        
        //datetime을 이용해서 csv에 일자별로 파일명으로 만들기

    }
}
