package com.kafka.chat.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Message {

    public static final String ENTER_MSG = " 님이 입장하셨습니다.";
    public static final String EXIT_MSG = " 님이 퇴장하셨습니다.";

    // 0 : chat, 1: info
    private int state;
    private User user;
    private String msg;
    private LocalDateTime time = LocalDateTime.now();
}
