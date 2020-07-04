package com.example.whereto.ui.main.settings

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController

import com.example.whereto.R
import com.example.whereto.ui.main.MainActivity
import com.example.whereto.ui.startup.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_change_name.view.*
import kotlinx.android.synthetic.main.dialog_change_password.view.*
import kotlinx.android.synthetic.main.register_fragment.*
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : Fragment(R.layout.settings_fragment) {

    private lateinit var mAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        settings_btn_change_name.setOnClickListener {

            val dialogView = layoutInflater.inflate(R.layout.dialog_change_name, null)

            AlertDialog.Builder(context)
                .setView(dialogView)
                .setPositiveButton("Set") { dialog, which ->

                    val updateInfo = UserProfileChangeRequest.Builder()
                        .setDisplayName(dialogView.dialog_et.text.toString())
                        .build()

                    mAuth.currentUser?.updateProfile(updateInfo)

                    dialog.cancel()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }

        settings_btn_change_password.setOnClickListener {

            val dialogView = layoutInflater.inflate(R.layout.dialog_change_password, null)

            AlertDialog.Builder(context)
                .setView(dialogView)
                .setPositiveButton("Set") { dialog, _ ->

                    if (dialogView.dialog_et_password.text.toString() == dialogView.dialog_et_confirm.text.toString()
                        && dialogView.dialog_et_password.text.toString().length >= 8) {


                        mAuth.currentUser?.updatePassword(dialogView.dialog_et_password.text.toString())

                        Toast.makeText(context, "Passwords successfuly changed!", Toast.LENGTH_SHORT).show()

                        dialog.cancel()

                    } else {

                        if (dialogView.dialog_et_password.text.toString().length < 8)
                            Toast.makeText(context, "Password must be at least 8 characters long!", Toast.LENGTH_LONG).show()
                        else
                            Toast.makeText(context, "Passwords don't match!", Toast.LENGTH_LONG).show()

                    }

                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }

        settings_btn_back.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToProfileFragment())
        }

        settings_btn_log_out.setOnClickListener {


            AlertDialog.Builder(context)
                .setTitle("Are you sure?")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes") { dialog, _ ->

                    dialog.cancel()

                    mAuth.signOut()

                    startActivity(
                        Intent(
                            requireContext(), LoginActivity::class.java
                        )
                    )
                    requireActivity().finish()
                }
                .setNegativeButton("Cancel") { dialog, _ ->

                    dialog.cancel()
                }
                .show()
        }

        settings_btn_delete_account.setOnClickListener {

            AlertDialog.Builder(context)
                .setTitle("Are you sure?")
                .setMessage("Are you sure you want to delete your account?")
                .setPositiveButton("Yes") { dialog, _ ->

                    dialog.cancel()

                    mAuth.currentUser?.delete()

                    startActivity(
                        Intent(
                            requireContext(), LoginActivity::class.java
                        )
                    )
                    requireActivity().finish()
                }
                .setNegativeButton("Cancel") { dialog, _ ->

                    dialog.cancel()
                }
                .show()
        }
    }
}
