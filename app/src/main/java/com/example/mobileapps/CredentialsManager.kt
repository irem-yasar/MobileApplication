package com.example.createaccountapp

class CredentialsManager {
    // Function to validate email format
    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    // Function to validate password
    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
}
