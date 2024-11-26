package com.example.mobileapps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileapps.CreateAccountActivity

class SecondPageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_second_page2)
        // Click listener for "Log In"
        //binding.alreadyMemberTextView.setOnClickListener {
        //    val intent = Intent(this, CreateAccountActivity::class.java)
        //    startActivity(intent)
        //}
    }
}

