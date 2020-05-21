package com.example.chatfb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chatfb.R.layout
import com.example.chatfb.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_new_messge.*
import kotlinx.android.synthetic.main.user_row_new_message.view.*

class NewMessage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_new_messge)

        supportActionBar?.title="Select User"
        fetchUsers()

        val adapter= GroupAdapter<GroupieViewHolder>()
        recyclerview_newmessage.adapter=adapter
    }
    companion object {
        val USER_KEY = "USER_KEY"
    }
    private fun fetchUsers() {
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<GroupieViewHolder>()

                p0.children.forEach {
                    Log.d("NewMessage", it.toString())
                    val user = it.getValue(User::class.java)
                    if (user != null) {
                        adapter.add(UserItem(user))
                    }
                }

                adapter.setOnItemClickListener { item, view ->

                    val userItem = item as UserItem

                    val intent = Intent(view.context, ChatLog::class.java)
//          intent.putExtra(USER_KEY,  userItem.user.username)
                    intent.putExtra(USER_KEY, userItem.user)
                    startActivity(intent)

                    finish()

                }

                recyclerview_newmessage.adapter = adapter
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}

class UserItem(val user: User): Item<GroupieViewHolder>() {
    override fun bind(viewHolder:GroupieViewHolder, position: Int) {
        viewHolder.itemView.username.text = user.username


    }

    override fun getLayout(): Int {
        return R.layout.user_row_new_message
    }
}
