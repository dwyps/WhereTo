package com.example.whereto.ui.startup.loginscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.whereto.R
import com.example.whereto.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_fragment.*
import timber.log.Timber


class LoginFragment : Fragment(R.layout.login_fragment) {

    private lateinit var mAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        val currentUser = mAuth.currentUser

        if (currentUser != null) {
            signInUI()
        }

        login_btn_sign_up.setOnClickListener {

            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        //TODO LOGIN ERROR MESSAGES
        login_btn_login.setOnClickListener {

            val email = login_edit_text_email.text.toString()
            val password = login_edit_text_password.text.toString()

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = mAuth.currentUser
                    if (user != null) {
                        signInUI()
                    }
                } else {

                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                    Timber.e(it.exception?.message)
                }
            }

        }
    }

    private fun signInUI() {

        startActivity(Intent(
            requireContext(), MainActivity::class.java
        ))
        requireActivity().finish()
    }
}