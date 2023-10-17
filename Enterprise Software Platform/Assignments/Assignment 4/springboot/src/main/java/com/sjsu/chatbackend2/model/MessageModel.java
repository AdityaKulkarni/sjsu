package com.sjsu.chatbackend2.model;

public class MessageModel {
    private String name;
    private String message;
    private String timestamp;
    private MessageType type;

    public enum MessageType {
        CHAT("chat"),
        JOIN("join"),
        LEAVE("leave");

        public final String label;

        private MessageType(String label) {
            this.label = label;
        }
    }

    public MessageModel(String name, String message, String timestamp, String type) {
        this.name = name;
        this.message = message;
        this.timestamp = timestamp;
        switch(type){
            case "chat", "CHAT":
                this.type = MessageType.CHAT;
                break;
            case "join", "JOIN":
                this.type = MessageType.JOIN;
                break;
            case "leave", "LEAVE":
                this.type = MessageType.LEAVE;
                break;
            default:
                this.type = MessageType.CHAT;
                break;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message; 
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public MessageType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
