package com.kafka.chat.dto;

import lombok.Data;

@Data
public class Message {
    private String id;
    private String msg;

    public String toJson(){
        StringBuilder json = new StringBuilder();
        json.append("{\"id\" : \"" + this.id + "\", ");
        json.append("\"msg\" : \"" + this.msg + "\" }");
        return json.toString();
    }
}
