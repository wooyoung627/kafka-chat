package com.kafka.chat.service;

import com.kafka.chat.dto.Message;
import com.kafka.chat.util.KafkaUtil;
import com.kafka.chat.websocket.WebSocketHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final KafkaUtil kafkaUtil;
    private final WebSocketHandler webSocketHandler;

    public void sendChat(Message msg){
        kafkaUtil.sendChat(msg.toJson());
    }

    @KafkaListener(id="chat_group", topics="chat")
    public void consume(
            @Payload String msg,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long ts) {
        log.debug("Consumed Message : " + msg);
        log.debug("Consumed topic : " + topic);

        try {
            webSocketHandler.sendMsg(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
