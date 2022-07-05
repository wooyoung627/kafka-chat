package com.wylee.proj;

import com.wylee.proj.entity.User;
import com.wylee.proj.repository.UserDBRepository;
import com.wylee.proj.security.JwtTokenProvider;
import com.wylee.proj.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class userTest {
    @Autowired
    private UserDBRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private UserService userService;

//    @Test
//    void insertUser(){
//        User user = new User("id", "pw", "nick");
//        userRepo.save(user);
//    }
//
//    @Test
//    void insertUserPwEncode(){
//        User user = new User("id1", passwordEncoder.encode("pw1"), "nick1");
//        userRepo.save(user);
//    }
//
//    @Test
//    void matchUser(){
//        User user = userRepo.findById("id1").get();
//        System.out.println(user);
//        assertTrue(passwordEncoder.matches("pw1", user.getPassword()));
//    }
//
//    @Test
//    void test(){
//        assertTrue("1".equals("0"));
//    }
//
//    @Test
//    void getUser(){
//        User user = userRepo.findById("id").get();
//
//        assertThat(user.getId()).isEqualTo("id");
//        assertThat(user.getPassword()).isEqualTo("pw");
//        assertThat(user.getNickname()).isEqualTo("nick");
//    }
//    
//    @Test
//    void generateToken() {
//    	User user = new User("id", "pw", "nick");
//    	String token = JwtTokenProvider.generateToken(user);
//    	System.out.println("token  : " + token);
//    }
    @Test
    void signIn() {
    	User user = new User("id", "pw2", "nick");
    	User res = userService.auth(user);
    	
//    	assertThat(user.getId()).isEqualTo(res.getId());
//    	assertTrue(passwordEncoder.matches(user.getPassword(), res.getPassword()));
    }
}
