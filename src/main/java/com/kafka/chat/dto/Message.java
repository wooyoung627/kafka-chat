package com.kafka.chat.dto;

import lombok.Data;

@Data
public class Message {
    private User user;
    private String msg;

    public String toJson(){
        StringBuilder json = new StringBuilder();
        json.append("{\"user\" : " + this.user.toJson() + " , ");
        json.append("\"msg\" : \"" + this.msg + "\" }");
        return json.toString();
    }
}
