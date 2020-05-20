package com.example.chatfb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatfb.R.layout
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_new_messge.*


class NewMessage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_new_messge)

        supportActionBar?.title="Select User"

        val adapter= GroupAdapter<GroupieViewHolder>()


        adapter.add(UserItem())
        adapter.add(UserItem())
        adapter.add(UserItem())
        adapter.add(UserItem())
        adapter.add(UserItem())


        recycleview.adapter=adapter
    }

}
class UserItem(): Item<GroupieViewHolder>() {
    override fun bind(viewHolder:GroupieViewHolder, position: Int) {
       // viewHolder.itemView.UserName.text = user.username


    }

    override fun getLayout(): Int {
        return R.layout.user_row_new_message
    }
}
