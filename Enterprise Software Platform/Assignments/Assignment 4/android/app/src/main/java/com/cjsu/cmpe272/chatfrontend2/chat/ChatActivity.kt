package com.cjsu.cmpe272.chatfrontend2.chat

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cjsu.cmpe272.chatfrontend2.R
import com.cjsu.cmpe272.chatfrontend2.databinding.ActivityChatBinding
import com.cjsu.cmpe272.chatfrontend2.models.MessageModel
import com.cjsu.cmpe272.chatfrontend2.models.MessageType
import com.google.gson.Gson
import ua.naiksoftware.stomp.StompClient

@SuppressLint("CheckResult")
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private var isConnectMessageSent = false;
    private lateinit var stompClient: StompClient
    private var name: String? = null
    private lateinit var messageModels: MutableList<MessageModel>

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        name = intent.extras?.getString("name")
        messageModels = mutableListOf()
        stompClient = StompClientSingleton.getInstance()

        with(binding) {
            rvMessages.layoutManager =
                LinearLayoutManager(this@ChatActivity, LinearLayoutManager.VERTICAL, false)
            rvMessages.adapter = MessagesAdapter(messageModels)

            btnSend.setOnClickListener {
                if (etMessage.text.toString().isEmpty()) {
                    etMessage.error = getString(R.string.enter_your_message)
                } else {
                    sendMessage(
                        MessageModel(
                            name!!,
                            etMessage.text.toString(),
                            System.currentTimeMillis().toString(),
                            MessageType.CHAT
                        )
                    )
                }
            }

        }
        with(stompClient) {
            topic("/topic/chat").subscribe {
                with(Gson()) {
                    Log.e("TAG", "onCreate: " + it)
                    val messageModel = fromJson(it.payload, MessageModel::class.java)
                    this@ChatActivity.runOnUiThread {
                        addMessageAndNotify(messageModel)
                    }
                }
            }

            if (isConnected) {
                name?.let { nonNullName ->
                    sendMessage(
                        MessageModel(
                            nonNullName,
                            "Joined",
                            System.currentTimeMillis().toString(),
                            MessageType.JOIN
                        )
                    )
                }
            }
        }
    }

    private fun sendMessage(messageModel: MessageModel) {
        stompClient.send(
            "/topic/chat", Gson().toJson(messageModel).toString()
        ).subscribe(({
            if (messageModel.type == MessageType.JOIN) {
                isConnectMessageSent = true
            }
            this@ChatActivity.runOnUiThread {
                binding.etMessage.setText("")
            }
        }), ({ throwable ->
            this@ChatActivity.runOnUiThread {
                Toast.makeText(
                    this@ChatActivity, getString(R.string.failed_to_connect), Toast.LENGTH_SHORT
                ).show()
                finish()
            }
            throwable.printStackTrace()
        }))
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addMessageAndNotify(messageModel: MessageModel) {
        if (!messageModels.contains(messageModel)) {
            messageModels.add(messageModel)
            binding.rvMessages.adapter?.notifyDataSetChanged()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        sendMessage(
            MessageModel(
                name ?: "A User",
                "Left",
                System.currentTimeMillis().toString(),
                MessageType.LEAVE
            )
        )
    }
}