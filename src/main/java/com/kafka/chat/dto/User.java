package com.kafka.chat.dto;

import lombok.Data;

@Data
public class User {

    private String id;
    private String nickname;

    public String toJson(){
        StringBuilder json = new StringBuilder();
        json.append("{\"id\" : \"" + this.id + "\", ");
        json.append("\"nickname\" : \"" + this.nickname + "\" }");
        return json.toString();
    }
}
