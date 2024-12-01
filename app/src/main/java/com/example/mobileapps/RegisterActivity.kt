package com.example.mobileapps

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class RegisterActivity : AppCompatActivity() {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page2) // Layout of the registration page

        // Initialize CredentialsManager
        credentialsManager = CredentialsManager(this)

        // Link fields from XML
        val fullNameInput = findViewById<TextInputEditText>(R.id.fullNameEditText)
        val emailInput = findViewById<TextInputEditText>(R.id.validEmailEditText)
        val passwordInput = findViewById<TextInputEditText>(R.id.strongPasswordEditText)
        val termsCheckBox = findViewById<MaterialCheckBox>(R.id.rememberMeCheckBox)
        val nextButton = findViewById<MaterialButton>(R.id.nextButton)
        val alreadyMemberTextView = findViewById<MaterialTextView>(R.id.alreadyMemberTextView) // Button "Already a member?"

        // Listener for the "NEXT" button
        nextButton.setOnClickListener {
            val fullName = fullNameInput.text.toString()

            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val agreedToTerms = termsCheckBox.isChecked

            // Validate fields
            if (fullName.isEmpty()) {
                showToast("Full name cannot be empty")
                return@setOnClickListener
            }

            if (!credentialsManager.isEmailValid(email)) {
                showToast("Invalid email format")
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                showToast("Password cannot be empty")
                return@setOnClickListener
            }

            if (!agreedToTerms) {
                showToast("You must agree to the terms and conditions")
                return@setOnClickListener
            }

            // Register the user
            val isRegistered = credentialsManager.register(email, password)
            if (isRegistered) {
                showToast("Registration was successful! You can now log in.")

                // Automatically navigate to LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

                // Finish the current activity
                finish()
            } else {
                showToast("The email is already in use. Please try another.")
            }
        }

        // Listener for the "Already a member? Log In" button
        alreadyMemberTextView.setOnClickListener {
            // Navigate to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Finish the current activity
        }
    }

    // Helper method for Toast messages
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}