package com.wylee.proj.service;

import com.wylee.proj.entity.User;
import com.wylee.proj.repository.UserDBRepository;
import lombok.RequiredArgsConstructor;
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
}
