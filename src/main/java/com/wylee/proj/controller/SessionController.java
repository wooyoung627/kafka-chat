package com.wylee.proj.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SessionController {
	static Logger log = LoggerFactory.getLogger(SessionController.class);
	
	private final Environment environment;
	
	@GetMapping("/port")
	public String port() {
		String res = "Port : " + environment.getProperty("server.port");
		log.debug(res);
		return res;
	}
	
	@GetMapping("/session-id")
	public String sessionId(HttpSession session) {
		String sessionId = session.getId();
		log.debug("PORT " + environment.getProperty("server.port") + " SESSION ID [" +sessionId + "]");
		return session.getId();
	}
}
