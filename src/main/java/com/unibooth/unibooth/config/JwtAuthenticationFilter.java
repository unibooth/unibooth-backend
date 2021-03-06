package com.unibooth.unibooth.config;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token  = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        System.out.println("token = " + token);
        if(token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        else if(token != null && !jwtTokenProvider.validateToken(token)) {
            // 애초에 false를 반환하지 않고 안에서 에러처리 진행하면 여기로 오지않을듯?
            SecurityContextHolder.getContext().setAuthentication(null);
            System.out.println("토큰 not validate");
        }
        else {
            SecurityContextHolder.getContext().setAuthentication(null);
            System.out.println("토큰이 없음");
        }

        chain.doFilter(request, response);
    }



}
