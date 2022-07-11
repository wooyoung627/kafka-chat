package com.wylee.proj.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.wylee.proj.domain.User;
import com.wylee.proj.util.CookieUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	private final CookieUtil cookieUtil;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
			log.debug("@@ LoginSuccessHandler @@");
			User user = (User) authentication.getPrincipal();
			String token = JwtTokenProvider.generateAccToken(user);
			response.addCookie(cookieUtil.createCookie(JwtTokenProvider.ACC_JWT_NAME, token, true, JwtTokenProvider.ACC_JWT_EXPIRATION_MS));
	}
}
