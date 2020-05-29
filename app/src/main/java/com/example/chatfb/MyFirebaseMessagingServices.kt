package com.example.chatfb

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService()

    fun onMessageReceived(remoteMessage: RemoteMessage) {
    if (remoteMessage.notification != null) {

        Log.d("FCM", remoteMessage.data.toString())
    }
}
