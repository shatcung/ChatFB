package com.example.chatfb

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId

class RegIntentService :

    IntentService("RegIntentService") {
        override fun onHandleIntent(intent: Intent?) {
            val token = FirebaseInstanceId.getInstance().token
            Log.i("RegIntentService","FCM Registration Token: $token")
        }
    }


