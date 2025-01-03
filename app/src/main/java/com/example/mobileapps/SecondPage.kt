package com.example.mobileapps

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page2)

        // Find the "Already Member" TextView and set the onClickListener
        val alreadyMemberTextView = findViewById<TextView>(R.id.alreadyMemberTextView)
        alreadyMemberTextView.setOnClickListener {
            // Check if we're already on SecondPage, if so, stay, if not, navigate back
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }
}
