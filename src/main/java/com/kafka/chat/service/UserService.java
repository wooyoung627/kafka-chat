package com.kafka.chat.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kafka.chat.domain.User;
import com.kafka.chat.repository.UserDBRepository;

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
}
