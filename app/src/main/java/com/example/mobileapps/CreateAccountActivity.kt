package com.example.mobileapps
import android.widget.Button
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText

import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_page2)

        credentialsManager = CredentialsManager(this)  // Initialize CredentialsManager

//       val loginLabel = findViewById<TextView>(R.id.loginLabel)
//        loginLabel.setOnClickListener {
//            Log.d("onboarding", "Login pressed")
//            val intent = Intent(this, SecondPage::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
//            startActivity(intent)
//        }

        val registerButton = findViewById<Button>(R.id.nextButton)  // Assuming you have a Button for registration
        registerButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.validEmailEditText).text.toString()
            val password = findViewById<EditText>(R.id.strongPasswordEditText).text.toString()

            if (credentialsManager.isEmailValid(email) && credentialsManager.isPasswordValid(password)) {
                val isRegistered = credentialsManager.register(email, password)
                if (isRegistered) {
                    Log.d("onboarding", "User registered successfully")
                    // Navigate to login or home page
                    val intent = Intent(this, SecondPage::class.java)
                    startActivity(intent)
                } else {
                    Log.d("onboarding", "Email already registered")
                    // Show error message to user
                }
            } else {
                Log.d("onboarding", "Invalid email or password")
                // Show error message to user
            }
        }
    }
}