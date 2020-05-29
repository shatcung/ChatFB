package com.example.chatfb

data class ChatChannel(val userIds: MutableList<String>) {
    constructor() : this(mutableListOf())
}