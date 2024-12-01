package com.example.mobileapps

import android.content.Context
import android.content.SharedPreferences
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

class CredentialsManagerTest {

    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        // Mock the Context and SharedPreferences
        val mockContext = Mockito.mock(Context::class.java)
        val mockSharedPreferences = Mockito.mock(SharedPreferences::class.java)

        // Mock behavior for SharedPreferences
        Mockito.`when`(mockContext.getSharedPreferences("user_credentials", Context.MODE_PRIVATE))
            .thenReturn(mockSharedPreferences)

        val credentialsManager = CredentialsManager(mockContext)
        val isEmailValid = credentialsManager.isEmailValid("")
        assertFalse(isEmailValid)
    }

    @Test
    fun givenWrongFormatEmail_thenReturnFalse() {
        val mockContext = Mockito.mock(Context::class.java)
        val mockSharedPreferences = Mockito.mock(SharedPreferences::class.java)

        Mockito.`when`(mockContext.getSharedPreferences("user_credentials", Context.MODE_PRIVATE))
            .thenReturn(mockSharedPreferences)

        val credentialsManager = CredentialsManager(mockContext)
        val isEmailValid = credentialsManager.isEmailValid("incorrectEmailFormat")
        assertFalse(isEmailValid)
    }

    @Test
    fun givenProperEmail_thenReturnTrue() {
        val mockContext = Mockito.mock(Context::class.java)
        val mockSharedPreferences = Mockito.mock(SharedPreferences::class.java)

        Mockito.`when`(mockContext.getSharedPreferences("user_credentials", Context.MODE_PRIVATE))
            .thenReturn(mockSharedPreferences)

        val credentialsManager = CredentialsManager(mockContext)
        val isEmailValid = credentialsManager.isEmailValid("test@example.com")
        assertTrue(isEmailValid)
    }

    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val mockContext = Mockito.mock(Context::class.java)
        val mockSharedPreferences = Mockito.mock(SharedPreferences::class.java)

        Mockito.`when`(mockContext.getSharedPreferences("user_credentials", Context.MODE_PRIVATE))
            .thenReturn(mockSharedPreferences)

        val credentialsManager = CredentialsManager(mockContext)
        val isPasswordValid = credentialsManager.isPasswordValid("")
        assertFalse(isPasswordValid)
    }

    @Test
    fun givenFilledPassword_thenReturnTrue() {
        val mockContext = Mockito.mock(Context::class.java)
        val mockSharedPreferences = Mockito.mock(SharedPreferences::class.java)

        Mockito.`when`(mockContext.getSharedPreferences("user_credentials", Context.MODE_PRIVATE))
            .thenReturn(mockSharedPreferences)

        val credentialsManager = CredentialsManager(mockContext)
        val isPasswordValid = credentialsManager.isPasswordValid("somepassword")
        assertTrue(isPasswordValid)
    }
}
