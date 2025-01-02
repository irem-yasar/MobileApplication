package com.example.mobileapps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {

    lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the shared CredentialsManager
        credentialsManager = CredentialsManager(this)

        // Load LoginFragment initially
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, LoginFragment.newInstance())
            }
        }
    }

    fun navigateToRegister() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, RegisterFragment.newInstance())
            addToBackStack(null)
        }
    }

    fun navigateToLogin() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, LoginFragment.newInstance())
            addToBackStack(null)
        }
    }
}

fun commit() {
    TODO("Not yet implemented")
}