package com.example.createaccountapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.createaccountapp.databinding.ActivitySecondPage2Binding

class SecondPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondPage2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondPage2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Click listener for "Log In"
        binding.alreadyMemberTextView.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }
}

