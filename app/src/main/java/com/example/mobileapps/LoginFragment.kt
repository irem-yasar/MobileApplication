package com.example.mobileapps

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {

    private lateinit var credentialsManager: CredentialsManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoginActivity) {
            credentialsManager = context.credentialsManager
        } else {
            throw IllegalStateException("Activity must be of type LoginActivity")
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Link views
        val emailInput = view.findViewById<TextInputEditText>(R.id.emailEditText)
        val passwordInput = view.findViewById<TextInputEditText>(R.id.passwordEditText)
        val nextButton = view.findViewById<MaterialButton>(R.id.nextButton)
        val newMemberTextView = view.findViewById<View>(R.id.newMemberTextView)

        // Set up the "Next" button
        nextButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (!credentialsManager.isEmailValid(email)) {
                Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!credentialsManager.isPasswordValid(password)) {
                Toast.makeText(context, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (credentialsManager.areCredentialsValid(email, password)) {
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                // Navigate to the next screen
            } else {
                Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up "New Member" text view
        newMemberTextView.setOnClickListener {
            (activity as? LoginActivity)?.navigateToRegister()
        }

        return view
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}
