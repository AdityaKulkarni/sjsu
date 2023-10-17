package com.cjsu.cmpe272.chatfrontend2.chat

import com.cjsu.cmpe272.chatfrontend2.models.MessageModel
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient

class StompClientSingleton {

    companion object {
        private var stompClient: StompClient? = null
        fun getInstance(): StompClient {
            if (stompClient === null) {
                stompClient = Stomp.over(
                    Stomp.ConnectionProvider.JWS, "ws://10.0.0.149:8080/chat/websocket"
                )
            }
            return stompClient!!
        }
    }
}