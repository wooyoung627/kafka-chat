package com.wylee.proj.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
	final int DEFAULT_MAX_AGE = 1000 * 60 * 60 * 24 * 7;
	
	public Cookie createCookie(String cookieName, String value) {
		return createCookie(cookieName, value, false);
	}

	public Cookie createCookie(String cookieName, String value, boolean httpOnly) {
		return createCookie(cookieName, value, httpOnly, DEFAULT_MAX_AGE);
	}
	
	public Cookie createCookie(String cookieName, String value, boolean httpOnly, long refresh_token_time){
	    Cookie token = new Cookie(cookieName,value);
    	token.setHttpOnly(httpOnly);
    	token.setMaxAge((int)(refresh_token_time/1000));
	    token.setPath("/");
	    return token;
	}
	
	public Cookie deleteCookie(String cookieName) {
		Cookie token = new Cookie(cookieName, "");
		token.setMaxAge(0);
		token.setPath("/");
		return token;
	}
	
	public Cookie getCookie(HttpServletRequest req, String cookieName){
	    final Cookie[] cookies = req.getCookies();
	    if(cookies==null) return null;
	    for(Cookie cookie : cookies){
	        if(cookie.getName().equals(cookieName))
	            return cookie;
	    }
	    return null;
	}
}
