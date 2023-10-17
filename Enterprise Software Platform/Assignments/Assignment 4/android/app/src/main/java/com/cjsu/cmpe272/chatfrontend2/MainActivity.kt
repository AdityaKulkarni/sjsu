package com.cjsu.cmpe272.chatfrontend2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cjsu.cmpe272.chatfrontend2.chat.ChatActivity
import com.cjsu.cmpe272.chatfrontend2.chat.StompClientSingleton
import com.cjsu.cmpe272.chatfrontend2.databinding.ActivityMainBinding
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.simpleName
    private lateinit var binding: ActivityMainBinding
    private lateinit var stompClient: StompClient

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        stompClient = StompClientSingleton.getInstance()

        stompClient.lifecycle().subscribe { lifecycleEvent ->
            when (lifecycleEvent.type) {
                LifecycleEvent.Type.OPENED -> {
                    Log.e(TAG, "Stomp connection opened")
                    startChatActivity()
                }

                LifecycleEvent.Type.ERROR -> {
                    Log.e(
                        TAG, "Error", lifecycleEvent.getException()
                    )
                }

                LifecycleEvent.Type.CLOSED -> {
                    Log.e(TAG, "Stomp connection closed")
                }

                LifecycleEvent.Type.FAILED_SERVER_HEARTBEAT -> {
                    Log.e(
                        TAG, "Failed server heartbeat"
                    )
                }
            }
        }

        with(binding) {

            etName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    tilName.error = null
                }
            })

            btnEnter.setOnClickListener(View.OnClickListener {
                if (etName.text.toString().isEmpty()) {
                    tilName.error = getString(R.string.enter_your_name)
                } else {
                    if (stompClient.isConnected) {
                        startChatActivity()
                    } else {
                        stompClient.connect()
                    }
                }
            })
        }
    }

    private fun startChatActivity() {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("name", binding.etName.text.toString())
        startActivity(intent)
    }
}