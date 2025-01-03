package com.example.mobileapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class CreateAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_account)

        // Find the "Already a Member" label and set the onClickListener to go to LoginPage
        val loginLabel = findViewById<TextView>(R.id.loginLabel)
        loginLabel.setOnClickListener {
            Log.d("onboarding", "Login pressed")
            // This will navigate back to the SecondPage activity
            val intent = Intent(this, SecondPage::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        // Find the "New Member Register Here" label and set the onClickListener
        val registerLabel = findViewById<TextView>(R.id.newMemberTextView)  // Assuming this is your "New Member Register Here"
        registerLabel.setOnClickListener {
            Log.d("onboarding", "New Member Register pressed")
            // This will navigate to SecondPage activity
            val intent = Intent(this, SecondPage::class.java)
            startActivity(intent)
        }
    }
}
