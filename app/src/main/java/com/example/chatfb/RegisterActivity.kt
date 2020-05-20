package com.example.chatfb


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        VerifyUserIsLoggedIn()
        auth = Firebase.auth

        button_Reg!!.setOnClickListener {
            val email = email_edit_text2.text.toString()

            val password = password_edit_text2.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            auth = FirebaseAuth.getInstance()
            Log.d("RegisterActivity", "Email is: " + email)
            Log.d("RegisterActivity", "Password: $password")

            // Firebase Authentication to create a user with email and password
            auth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("RegisterActivity", "createUserWithEmail:success")
                        val user = auth!!.currentUser

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("RegisterActivity", "createUserWithEmail:failure")


                    }
                }
            saveUserToFirebaseDatabase(it.toString())
        }

       Login.setOnClickListener {
            Log.d("RegisterActivity", "Try to show LoginActivity")

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
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
    private  fun  saveUserToFirebaseDatabase(toString: String) {

        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/uid$uid")

        val intent = Intent(this,LatestMessages::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

        val user = User(uid, username_edit_text.text.toString(),email_edit_text2.text.toString())
        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("RegisterActivity", "Finally we saved the user to Firebase Database")
            }
            .addOnFailureListener {
                Log.d("RegisterActivity", "Failed to set value to database: ${it.message}")
            }
    }

}

