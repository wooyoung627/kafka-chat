package com.kafka.chat;

import com.kafka.chat.util.KafkaUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class kafkaTest {

    @Autowired
    private KafkaUtil kafkaUtil;

    final String TOPIC = "test";

    @Test
    void send(){
        String msg = "send msg";

        kafkaUtil.sendMsg(TOPIC, msg);
    }
}
