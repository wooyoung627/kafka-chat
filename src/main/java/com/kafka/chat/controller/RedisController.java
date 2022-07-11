package com.kafka.chat.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.kafka.chat.domain.User;
import com.kafka.chat.repository.UserRedisRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class RedisController {
	
	static Logger log = LoggerFactory.getLogger(RedisController.class);
	
	private final UserRedisRepository repo;
	
	@GetMapping("/user")
	public User getUser(@RequestParam String id) {
		Optional<User> find = repo.findById(id);
		return find.get()!=null ? find.get() : null;
	}
	
	@PostMapping("/user")
	public User postUser(@RequestBody User param) {
		log.debug("POST USER : " + param);
		User user = new User(param.getId(), param.getPassword(), "");
		return repo.save(user);
	}
	
	@DeleteMapping("/user")
	public void deleteUser(@RequestBody User user) {
		log.debug("DELETE USER : " + user);
		repo.delete(user);
	}
}
