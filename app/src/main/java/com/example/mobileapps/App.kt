package com.example.mobileapps

import android.app.Application

class App : Application() {
    companion object {
        lateinit var credentialsManager: CredentialsManager
            private set
    }

    override fun onCreate() {
        super.onCreate()
        // Initialize the singleton instance of CredentialsManager
        credentialsManager = CredentialsManager(applicationContext)
    }
}
