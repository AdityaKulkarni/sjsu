package com.sjsu.chatbackend2.controller;

import org.springframework.stereotype.Controller;
import com.sjsu.chatbackend2.model.MessageModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.json.JSONObject;

@Controller
public class ChatController {
    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public MessageModel sendMessage(String message) throws Exception {
        System.out.println("new message: " + message);
        JSONObject msgObject = new JSONObject(message);
        return new MessageModel(msgObject.getString("name"), msgObject.getString("message"),
            msgObject.getString("timestamp"), msgObject.getString("type"));
    }
}
