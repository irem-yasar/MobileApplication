package com.example.mobileapps

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account) // Correctly linked layout file

        // Initialize CredentialsManager
        credentialsManager = CredentialsManager(this)

        // Link views from XML
        val emailInput = findViewById<TextInputEditText>(R.id.emailEditText)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordEditText)
        val nextButton = findViewById<MaterialButton>(R.id.nextButton)
        val newMemberTextView = findViewById<TextView>(R.id.newMemberTextView)

        // Set up "Next" button click listener
        nextButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            // Validate email and password
            if (!credentialsManager.isEmailValid(email)) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!credentialsManager.isPasswordValid(password)) {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if credentials match
            if (credentialsManager.areCredentialsValid(email, password)) {
                // Navigate to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up "New Member" text click listener
        newMemberTextView.setOnClickListener {
            navigateToRegister()
        }
    }

    private fun navigateToRegister() {
        // Navigate to registration page
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}