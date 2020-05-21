package com.example.chatfb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        VerifyUserIsLoggedIn()
        button_log.setOnClickListener {

            performLogin()


        }
            Registration.setOnClickListener {
                Log.d("LoginActivity", "Try to show RegisterActivity")
                finish()
            }
        }

    private fun VerifyUserIsLoggedIn() {
        val uid=FirebaseAuth.getInstance().uid
        if(uid!==null){
            val intent=Intent(this,LatestMessages::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun performLogin() {
        val email = email_edit_text2.text.toString()
        val password = password_edit_text2.text.toString()
        Log.d("LoginActivity", "Email: " + email)
        Log.d("LoginActivity", "Password $password")

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill out email/pw.", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                Log.d("Login", "Successfully logged in: ${it.result?.user?.uid}")
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to log in: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        val intent = Intent(this,LatestMessages::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
