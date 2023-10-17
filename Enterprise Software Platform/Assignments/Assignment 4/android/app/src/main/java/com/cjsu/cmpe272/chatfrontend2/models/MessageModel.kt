package com.cjsu.cmpe272.chatfrontend2.models

import java.io.Serializable

enum class MessageType(value: String){
    CHAT("chat"),
    JOIN("join"),
    LEAVE("leave")
}
data class MessageModel(var name: String, var message: String, var timestamp: String, var type: MessageType): Serializable