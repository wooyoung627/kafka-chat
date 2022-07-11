package com.wylee.proj.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wylee.proj.domain.User;
import com.wylee.proj.repository.UserDBRepository;
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
		private final UserDBRepository userRepo;
	
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    		log.debug("@@ JwtAuthenticationFilter @@");
    		log.debug("url : " + request.getRequestURL());
    		
    		Cookie accCookie = cookieUtil.getCookie(request, JwtTokenProvider.ACC_JWT_NAME);
    		
    		if(accCookie != null && JwtTokenProvider.validateToken(accCookie.getValue())) {
    			String accToken = accCookie.getValue();
    			String id = JwtTokenProvider.getUserIdFromJWT(accToken);
    			User user = userRepo.findById(id).orElse(null);
    			
    			security(user, request);
    		}else {
    			response.addCookie(cookieUtil.deleteCookie(JwtTokenProvider.ACC_JWT_NAME));
    		}
    		
    		filterChain.doFilter(request, response);
    }
    
    private void security(User user, HttpServletRequest request) {
    	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    	usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    	SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
