package com.kafka.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.chat.dto.Message;
import com.kafka.chat.dto.User;
import com.kafka.chat.util.KafkaUtil;
import com.kafka.chat.websocket.WebSocketHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

import static com.kafka.chat.dto.Message.ENTER_MSG;
import static com.kafka.chat.dto.Message.EXIT_MSG;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {


    private final KafkaUtil<Message> kafkaUtil;
    private final WebSocketHandler webSocketHandler;
    private final ObjectMapper objectMapper;

    public void sendChat(Message msg){
        msg.setState(0);
        kafkaUtil.sendChat(msg);
    }

    @KafkaListener(id="chat_group", topics="chat")
    public void consume(
            @Payload Message msg,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long ts) {
        log.debug("Consumed Message : " + msg);
        log.debug("Consumed topic : " + topic);

        try {
            sendMsgWebSocket(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @KafkaListener(id = "test_group", topics = "test")
    public void consume(Message msg){
        log.debug("Consumed : " + msg);
        log.debug("time : " + msg.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    public void enterRoom(User user) {
        Message msg = new Message();
        msg.setUser(user);
        msg.setState(1);
        msg.setMsg(ENTER_MSG);

        try{
            sendMsgWebSocket(msg);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exitRoom(User user) {
        Message msg = new Message();
        msg.setUser(user);
        msg.setState(1);
        msg.setMsg(EXIT_MSG);

        try{
            sendMsgWebSocket(msg);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMsgWebSocket(Message msg) throws Exception{
        webSocketHandler.sendMsg(objectMapper.writeValueAsString(msg));
    }
}
