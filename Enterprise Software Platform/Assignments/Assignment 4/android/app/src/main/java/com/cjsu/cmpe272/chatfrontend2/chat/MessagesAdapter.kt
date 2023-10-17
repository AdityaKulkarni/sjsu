package com.cjsu.cmpe272.chatfrontend2.chat

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cjsu.cmpe272.chatfrontend2.databinding.RowJoinBinding
import com.cjsu.cmpe272.chatfrontend2.databinding.RowMessageBinding
import com.cjsu.cmpe272.chatfrontend2.models.MessageModel
import com.cjsu.cmpe272.chatfrontend2.models.MessageType

enum class ViewTypes(value: Int) {
    JOIN_LEAVE_TYPE(0),
    CHAT_TYPE(1)
}

class MessagesAdapter(private val messages: List<MessageModel>) :
    RecyclerView.Adapter<ViewHolder>() {

    class UserActivityViewHolder(private val binding: RowJoinBinding) : ViewHolder(binding.root) {

        fun bindView(messageModel: MessageModel) {
            binding.tvUserActivity.text =
                if (messageModel.type === MessageType.JOIN) "${messageModel.name} joined the chat!"
                else "${messageModel.name} left the chat!"
        }
    }

    class MessageViewHolder(private val binding: RowMessageBinding) : ViewHolder(binding.root) {

        fun bindView(messageModel: MessageModel) {
            with(binding) {
                tvName.text = "${messageModel.name}:"
                tvMessage.text = messageModel.message
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.e("TAG", "onCreateViewHolder: " + viewType)
        return when (viewType) {
            ViewTypes.JOIN_LEAVE_TYPE.ordinal -> UserActivityViewHolder(
                RowJoinBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            ViewTypes.CHAT_TYPE.ordinal -> MessageViewHolder(
                RowMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> MessageViewHolder(
                RowMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is UserActivityViewHolder -> holder.bindView(messages[position])
            is MessageViewHolder -> holder.bindView(messages[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (messages[position].type) {
            MessageType.JOIN, MessageType.LEAVE ->
                ViewTypes.JOIN_LEAVE_TYPE.ordinal

            MessageType.CHAT ->
                ViewTypes.CHAT_TYPE.ordinal
        }
    }
}