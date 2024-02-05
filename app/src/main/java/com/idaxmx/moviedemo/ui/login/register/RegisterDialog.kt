package com.idaxmx.moviedemo.ui.login.register

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.idaxmx.moviedemo.databinding.DialogRegisterBinding

class RegisterDialog(
    private val auth: FirebaseAuth,
    private val onSuccessful: (FirebaseUser?) -> Unit
) : DialogFragment() {

    private val TAG = this.javaClass.simpleName

    private var _binding: DialogRegisterBinding? = null
    private val binding get() = _binding!!

    private var wasAdded: Boolean = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogRegisterBinding.inflate(layoutInflater)

        listeners()

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    private fun listeners() {
        binding.registerButton.setOnClickListener {
            val email: String = binding.emailEditText.text.toString()
            val password: String = binding.passwordEditText.text.toString()
            if (email.isNotBlank() && password.isNotBlank()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmail:success")
                            Toast.makeText(this.context, "User registered", Toast.LENGTH_SHORT).show()
                            val user: FirebaseUser? = auth.currentUser
                            onSuccessful(user)
                            dismiss()
                        } else {
                            Toast.makeText(this.context, "Authentication failed", Toast.LENGTH_SHORT).show()
                            Log.d(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                requireContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else Toast.makeText(requireContext(), "Empty fields", Toast.LENGTH_SHORT).show()
        }

        binding.closeImageButton.setOnClickListener { dismiss() }
    }
}