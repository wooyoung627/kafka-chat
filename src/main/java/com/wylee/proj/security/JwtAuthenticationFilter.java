package com.wylee.proj.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wylee.proj.util.CookieUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

		private final CookieUtil cookieUtil;
	
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    		log.debug("@@ JwtAuthenticationFilter @@");
    		log.debug("url : " + request.getRequestURL());
    		
    		Cookie accCookie = cookieUtil.getCookie(request, ALREADY_FILTERED_SUFFIX);
    		
    		filterChain.doFilter(request, response);
    }
}
