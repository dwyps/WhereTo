package com.example.whereto.ui.main.settings

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.example.whereto.R
import com.example.whereto.ui.main.MainActivity
import com.example.whereto.ui.startup.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : Fragment(R.layout.settings_fragment) {

    private lateinit var mAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        btn_profile.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToProfileFragment())
        }

        settings_btn_log_out.setOnClickListener {
            mAuth.signOut()
            val currentUser = mAuth.currentUser
            if (currentUser == null) {

                startActivity(
                    Intent(
                    requireContext(), LoginActivity::class.java
                    )
                )
                requireActivity().finish()
            }
        }
    }
}
