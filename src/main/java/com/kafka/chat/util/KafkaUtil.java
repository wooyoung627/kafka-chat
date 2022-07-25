package com.kafka.chat.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaUtil {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final String CHAT_TOPIC = "chat";

    public void sendMsg(String topic, String msg){
      log.debug("KAFKA TOPIC : " + topic);
      log.debug("KAFKA MESSAGE : " + msg);

      kafkaTemplate.send(topic, msg);
    }

    public void sendChat(String msg){
        sendMsg(CHAT_TOPIC, msg);
    }
}
