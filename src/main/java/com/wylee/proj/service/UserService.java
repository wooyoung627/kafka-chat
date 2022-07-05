package com.wylee.proj.service;

import com.wylee.proj.entity.User;
import com.wylee.proj.repository.UserDBRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDBRepository userRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    public User getUser(String id){
        return userRepo.findById(id).orElse(null);
    }

    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    
    public User auth(User param) {
    	User user = userRepo.findById(param.getId()).orElseThrow(() -> new UsernameNotFoundException(param.getId()));
    		
    	if(!passwordEncoder.matches(param.getPassword(), user.getPassword())) {
    		throw new BadCredentialsException("Password not matched");
    	}
    	
    	return user;
    }
}
