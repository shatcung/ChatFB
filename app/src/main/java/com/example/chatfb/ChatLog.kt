package com.example.chatfb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.chatfb.R
import com.example.chatfb.User
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*

class ChatLog : AppCompatActivity() {



    class ChatLogActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_chat_log)

//    val username = intent.getStringExtra(NewMessageActivity.USER_KEY)
            val user = intent.getParcelableExtra<User>(NewMessage.USER_KEY)

            supportActionBar?.title = user.username

            val adapter = GroupAdapter<GroupieViewHolder>()

            adapter.add(ChatFromItem())
            adapter.add(ChatToItem())
            adapter.add(ChatFromItem())
            adapter.add(ChatToItem())
            adapter.add(ChatFromItem())
            adapter.add(ChatToItem())
            adapter.add(ChatFromItem())
            adapter.add(ChatToItem())

            recyclerview_chat_log.adapter = adapter
        }
    }

    class ChatFromItem: Item<GroupieViewHolder>() {
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        }

        override fun getLayout(): Int {
            return R.layout.chat_from_row
        }
    }

    class ChatToItem: Item<GroupieViewHolder>() {
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        }

        override fun getLayout(): Int {
            return R.layout.chat_to_row
        }
    }
}
