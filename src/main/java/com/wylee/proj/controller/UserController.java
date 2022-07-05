package com.wylee.proj.controller;

import com.wylee.proj.entity.User;
import com.wylee.proj.security.JwtTokenProvider;
import com.wylee.proj.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public User getUser(@RequestParam String id) throws Exception{
        log.debug("user id : " + id);
        return userService.getUser(id);
    	}

    @PostMapping
    public User postUser(@RequestBody User user){
        log.debug("user : " + user);
        return userService.saveUser(user);
    	}
    
    
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody User param) {
    		log.debug("sign in user : " + param);
    		User user = userService.auth(param);
    		String token = JwtTokenProvider.generateToken(user);
    		return ResponseEntity.ok(token);
    }
}
