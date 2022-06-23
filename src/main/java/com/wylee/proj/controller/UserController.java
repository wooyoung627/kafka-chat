package com.wylee.proj.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wylee.proj.entity.User;
import com.wylee.proj.repository.UserRedisRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	static Logger log = LoggerFactory.getLogger(UserController.class);
	
	private final UserRedisRepository repo;
	
	@GetMapping("/user")
	public User getUser(@RequestParam String id) {
		Optional<User> find = repo.findById(id);
		return find.get()!=null ? find.get() : null;
	}
	
	@PostMapping("/user")
	public User postUser(@RequestBody User param) {
		log.debug("POST USER : " + param);
		User user = new User(param.getId(), param.getPassword());
		return repo.save(user);
	}
	
	@DeleteMapping("/user")
	public void deleteUser(@RequestBody User user) {
		log.debug("DELETE USER : " + user);
		repo.delete(user);
	}
}
