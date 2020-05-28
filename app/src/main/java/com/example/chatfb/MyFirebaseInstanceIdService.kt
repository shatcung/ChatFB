package com.example.chatfb


import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService


class MyFirebaseMessagingService : FirebaseMessagingService() {


        fun onTokenRefresh(){
            val refreshedToken=FirebaseInstanceId.getInstance().token
            Log.d("MyFirebaseMessagingService","Refreshed token"+refreshedToken!!)
    }
}