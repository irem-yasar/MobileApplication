package com.example.mobileapps

import android.content.Context
import android.content.SharedPreferences

class CredentialsManager(
    private val context: Context? = null // Optional for testing
) {

    private val sharedPreferences: SharedPreferences = context?.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
        ?: throw IllegalArgumentException("Context must be provided for SharedPreferences")

    fun isEmailValid(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }

    fun register(email: String, password: String): Boolean {
        if (sharedPreferences.contains(email)) {
            return false // Email already exists
        }
        sharedPreferences.edit().putString(email, password).apply() // Save credentials
        return true // Registration successful
    }

    fun areCredentialsValid(email: String, password: String): Boolean {
        val savedPassword = sharedPreferences.getString(email, null)
        return savedPassword == password
    }
}

