package com.example.chatfb

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    val uid: String, val username: String, val email: String?,
    val profileImageUrl: String?, val registrationTokens: MutableList<String>
): Parcelable {
    constructor() : this("", " ", "",null, mutableListOf())
}