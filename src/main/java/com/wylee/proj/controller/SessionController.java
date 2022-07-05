package com.wylee.proj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequiredArgsConstructor
public class SessionController {
	
	private final Environment environment;
	
	@GetMapping("/test")
	public String test() {
		log.debug("** TEST **");
		return "TEST!";
	}
	
	@GetMapping("/port")
	public String port() {
		String res = "Port : " + environment.getProperty("server.port");
		log.debug(res);
		return res;
	}
	
	@GetMapping("/session-id")
	public String sessionId(HttpSession session, HttpServletRequest request) {
		String sessionId = session.getId();
		log.debug("URL : " +request.getRequestURL());
		log.debug("PORT " + environment.getProperty("server.port") + " SESSION ID [" +sessionId + "]");
		return session.getId();
	}
}
