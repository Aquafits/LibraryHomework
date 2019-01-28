package com.aquafits.library.utils.message;

import com.google.gson.Gson;
import lombok.Data;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Data
public class Message {
    private Calendar sendTime;
    private String channel;
    private String content;

    public Message(String whoseId, String channel, String content) {
        this.sendTime = Calendar.getInstance();
        this.channel = channel;

        Map<String, String> map = new HashMap<>();
        map.put("from_user", whoseId);
        map.put("content",content);

        this.content = new Gson().toJson(map);
    }
}
