package com.wylee.proj;

import com.wylee.proj.entity.User;
import com.wylee.proj.repository.UserDBRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class dbTest {
    @Autowired
    private UserDBRepository userRepo;

    @Test
    void insertUser(){
        User user = new User("id", "pw", "nick");
        userRepo.save(user);
    }
}
