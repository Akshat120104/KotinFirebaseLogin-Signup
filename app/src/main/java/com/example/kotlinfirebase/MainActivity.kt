package com.example.kotlinfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var mRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val edtmail = findViewById<EditText>(R.id.edtmail)
        val edtpass = findViewById<EditText>(R.id.edtpass)
        var btn1 = findViewById<Button>(R.id.btn1)
        var btn2 = findViewById<Button>(R.id.btn2)
        auth = FirebaseAuth.getInstance()
        mRef = FirebaseDatabase.getInstance().getReference("users")
        btn1.setOnClickListener {
            val email: String = edtmail.text.toString()
            val password: String = edtpass.text.toString()
            login(email,password)
        }
        btn2.setOnClickListener {
            val email: String = edtmail.text.toString()
            val password: String = edtpass.text.toString()
            signin(email,password)
        }


    }
    fun login(email: String,password: String)
    {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this,home::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "UNSUCCESSFUL", Toast.LENGTH_SHORT).show()

                }

            }
    }
    fun signin(email: String, password: String)
    {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
val intent = Intent(this,home::class.java)
                    startActivity(intent)
                    addUsertoDB(email)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "UNSUCCESSFUL", Toast.LENGTH_SHORT).show()

                }
            }
    }
    fun addUsertoDB(email: String)
    {
mRef.push().setValue(email)
    }
}