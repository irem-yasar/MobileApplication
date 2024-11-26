package com.example.mobileapps

import org.junit.Assert.*
import org.junit.Test



class CredentialsManagerTest {


    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val isEmailValid = credentialsManager.isEmailValid("")
        assertFalse(isEmailValid)
    }

    @Test
    fun givenWrongFormatEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val isEmailValid = credentialsManager.isEmailValid("incorrectEmailFormat")
        assertFalse(isEmailValid)
    }

    @Test
    fun givenProperEmail_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val isEmailValid = credentialsManager.isEmailValid("test@example.com")
        assertTrue(isEmailValid)
    }

    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val isPasswordValid = credentialsManager.isPasswordValid("")
        assertFalse(isPasswordValid)
    }

    @Test
    fun givenFilledPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val isPasswordValid = credentialsManager.isPasswordValid("somepassword")
        assertTrue(isPasswordValid)
    }

}