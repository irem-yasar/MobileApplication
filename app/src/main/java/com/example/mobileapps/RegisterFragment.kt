package com.example.mobileapps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class RegisterFragment : Fragment() {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        credentialsManager = (activity as LoginActivity).credentialsManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the fragment layout
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Link fields from XML
        val fullNameInput = view.findViewById<TextInputEditText>(R.id.fullNameEditText)
        val emailInput = view.findViewById<TextInputEditText>(R.id.validEmailEditText)
        val passwordInput = view.findViewById<TextInputEditText>(R.id.strongPasswordEditText)
        val termsCheckBox = view.findViewById<MaterialCheckBox>(R.id.rememberMeCheckBox)
        val nextButton = view.findViewById<MaterialButton>(R.id.nextButton)
        val alreadyMemberTextView = view.findViewById<MaterialTextView>(R.id.alreadyMemberTextView)

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

                // Navigate to LoginFragment
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, LoginFragment.newInstance())
                    .commit()
            } else {
                showToast("The email is already in use. Please try another.")
            }
        }

        // Listener for the "Already a member? Log In" button
        alreadyMemberTextView.setOnClickListener {
            // Navigate to LoginFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment.newInstance())
                .commit()
        }
    }

    // Helper method for Toast messages
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(): RegisterFragment {
            return RegisterFragment().apply {
                arguments = Bundle().apply {
                    // Add any necessary arguments here
                }
            }
        }
    }
}
