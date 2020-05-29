package com.example.chatfb


import android.content.Context
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_person.view.*

@GlideModule
class AppGlideModule : AppGlideModule()

class PersonItem(val person: User,
                 val userId: String,
                 private val context: Context)
    : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textView_name.text = person.username
        if (person.profileImageUrl != null)
            GlideApp.with(context)
                .load(StorageUtil.pathToReference(person.profileImageUrl))
                .placeholder(R.drawable.ic_account_circle_black_24dp)

    }

    override fun getLayout() = R.layout.user_row_new_message
}