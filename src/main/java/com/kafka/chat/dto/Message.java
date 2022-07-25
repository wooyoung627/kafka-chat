package com.kafka.chat.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Message {
    private User user;
    private String msg;

    private LocalDateTime time = LocalDateTime.now();

    public String toJson(){
        StringBuilder json = new StringBuilder();
        json.append("{\"user\" : " + this.user.toJson() + " , ");
        json.append("\"msg\" : \"" + this.msg + "\" ," +
                "\"time\" : \"" + this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+ "\" }");
        return json.toString();
    }
}
