package com.example.kotlinfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class home : AppCompatActivity() {
    private lateinit var mRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val listView = findViewById<ListView>(R.id.ls)
        val listItem = mutableListOf<String>()

        mRef= FirebaseDatabase.getInstance().getReference("users")

       val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listItem)

        mRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for(postSnapshot : DataSnapshot in snapshot.children)
                {
listItem.add(postSnapshot.value.toString())
                    arrayAdapter.notifyDataSetChanged()
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        listView.adapter = arrayAdapter
    }
}