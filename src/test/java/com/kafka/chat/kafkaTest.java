package com.kafka.chat;

import com.kafka.chat.dto.Message;
import com.kafka.chat.dto.User;
import com.kafka.chat.util.KafkaUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
public class kafkaTest {

    @Autowired
    private KafkaUtil kafkaUtil;

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    final String TOPIC = "test";

    @Test
    void send(){
        String msg = "send msg";

        kafkaUtil.sendMsg(TOPIC, msg);
    }

    @Test
    void sendJson(){
        User user = new User();
        user.setId("id");
        user.setNickname("nickname");

        Message msg = new Message();
        msg.setUser(user);
        msg.setMsg("hi");

        kafkaTemplate.send(TOPIC, msg);
    }
}
