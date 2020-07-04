package com.example.whereto.ui.startup.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.whereto.R
import com.example.whereto.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.register_fragment.*
import timber.log.Timber

class RegisterFragment : Fragment(R.layout.register_fragment) {

    private lateinit var mAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        //TODO REGISTER ERROR MESSAGES
        register_btn_register.setOnClickListener {

            if (register_edit_text_password.text.toString() == register_edit_text_check_password.text.toString()
                && register_edit_text_password.text.toString().length >= 8) {

                val name = register_edit_text_name.text.toString()
                val email = register_edit_text_email.text.toString()
                val password = register_edit_text_password.text.toString()

                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {

                            val user = mAuth.currentUser

                            val updateInfo = UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build()

                            if (user != null) {

                                user.sendEmailVerification()
                                user.updateProfile(updateInfo)

                                Toast.makeText(context, "Registration successful!",
                                    Toast.LENGTH_SHORT).show()

                                findNavController().popBackStack()
                            }
                        } else {

                            Timber.e(it.exception)
                            Toast.makeText(context, "Authentication failed.",
                                Toast.LENGTH_LONG).show()
                        }
                    }

            } else {

                if (register_edit_text_password.text.toString().length < 8)
                    Toast.makeText(context, "Password must be at least 8 characters long!", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(context, "Passwords don't match!", Toast.LENGTH_LONG).show()
            }
        }

    }
}