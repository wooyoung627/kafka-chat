package com.kafka.chat.websocket;

import com.kafka.chat.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    Map<String, WebSocketSession> storage = new HashMap<>();

    // 웹소켓 클라이언트가 텍스트 메시지를 전송할 때 호출
    // TextMessage : 텍스트 메시지의 정보
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info(session.getId() + " send " + message.getPayload());
    }

    // 클라이언트와 연결
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String id = session.getId();
        storage.put(id, session);

        log.info("Connect Success : " + id);
    }

    // 클라이언트와 연결 해제
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String id = session.getId();
        storage.remove(id);

        log.info("Connect Close : " + session.getId());
    }

    public synchronized void sendMsg(String msg) throws IOException {
        Set<String> keys = storage.keySet();

        for(String key : keys){
            WebSocketSession session = storage.get(key);
            if(session.isOpen())
                session.sendMessage(new TextMessage(msg));
        }
    }
}
