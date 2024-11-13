package com.team5.codulgiserver.global.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Component
public class CustomFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long initialTime = System.currentTimeMillis();

        // long을 Instant로 변환
        Instant instant = Instant.ofEpochMilli(initialTime);

        // Instant를 LocalDateTime으로 변환 (시스템 기본 시간대)
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());

        request.setAttribute("initialTime", dateTime);

        filterChain.doFilter(request, response);
    }
}
