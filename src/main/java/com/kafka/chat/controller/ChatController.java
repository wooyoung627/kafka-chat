package com.kafka.chat.controller;

import com.kafka.chat.dto.Message;
import com.kafka.chat.service.ChatService;
import com.kafka.chat.websocket.WebSocketHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final WebSocketHandler webSocketHandler;
    private final ChatService chatService;

    @PostMapping
    public void postChat(@RequestBody Message msg){

        log.debug("Message : " + msg);
        chatService.sendChat(msg);


//        try{
//            webSocketHandler.sendMsg(msg);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
