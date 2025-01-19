package com.example.mobileapps

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow

class CredentialsManager(
    private val context: Context
) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)

    private val _isLoggedIn = MutableStateFlow(isUserLoggedIn())

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
        return true
    }

    fun areCredentialsValid(email: String, password: String): Boolean {
        val savedPassword = sharedPreferences.getString(email, null)
        return savedPassword == password
    }

    fun logout() {
        sharedPreferences.edit().clear().apply()
        _isLoggedIn.value = false
    }

    private fun isUserLoggedIn(): Boolean {
        return sharedPreferences.all.isNotEmpty() // Basic check
    }

}
