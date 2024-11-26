package com.example.mobileapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private val inputEmail: TextInputLayout
        get() = findViewById(R.id.emailEditText)

    private val inputPassword: TextInputLayout
        get() = findViewById(R.id.passwordEditText)

    private val inputEmailText: String
        get() = findViewById<TextInputEditText>(R.id.emailEditText).text.toString()

    private val inputPasswordText: String
        get() = findViewById<TextInputEditText>(R.id.passwordEditText).text.toString()

    private val nextButton: Button
        get() = findViewById(R.id.nextButton)

    private val isEmailValid: Boolean
        get() = CredentialsManager().isEmailValid(inputEmailText)

    private val isPasswordValid: Boolean
        get() = CredentialsManager().isPasswordValid(inputPasswordText)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        nextButton.setOnClickListener {
            var isValid = true

            if (!isEmailValid) {
                isValid = false
                inputEmail.isErrorEnabled = true
                inputEmail.error = "Email is invalid!"
            } else {
                inputEmail.isErrorEnabled = false
            }

            if (!isPasswordValid) {
                isValid = false
                inputPassword.isErrorEnabled = true
                inputPassword.error = "Password is invalid!"
            } else {
                inputPassword.isErrorEnabled = false
            }

            if (isValid) {
                val email = inputEmailText
                val password = inputPasswordText

                if (email == "test@te.st" && password == "1234") {
                    val mainActivityIntent = Intent(this, SecondPage::class.java)
                    startActivity(mainActivityIntent)
                } else {
                    inputPassword.isErrorEnabled = true
                    inputPassword.error = "Incorrect email or password!"
                }
            }
        }

        val registerNowLabel = findViewById<TextView>(R.id.newMemberTextView)
        registerNowLabel.setOnClickListener {
            Log.d("onboarding", "Register now pressed")

            val intent = Intent(this, CreateAccountActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }
}
