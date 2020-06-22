package com.example.whereto.ui.main.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.whereto.R
import com.example.whereto.ui.main.profile.adapter.ProfileTabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.profile_fragment.*


class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var viewPagerAdapter: ProfileTabAdapter

    private val RESULT_LOAD_IMAGE: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        profile_tv_username.text = mAuth.currentUser?.displayName

        setProfilePicture(mAuth.currentUser?.photoUrl)

        profile_iv_profile_photo.setOnClickListener {

            val i = Intent(
                Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(i, RESULT_LOAD_IMAGE)
        }

        viewPagerAdapter = ProfileTabAdapter(this)

        profile_view_pager.adapter = viewPagerAdapter
        profile_view_pager.isUserInputEnabled = false
        profile_view_pager.requestDisallowInterceptTouchEvent(true)

        TabLayoutMediator(profile_tab_layout, profile_view_pager) { tab, position ->

            when(position) {

                0 -> {
                    tab.icon = resources.getDrawable(R.drawable.ic_visited)
                }

                1 -> {
                    tab.icon = resources.getDrawable(R.drawable.ic_saved)
                }
            }

        }.attach()

        btn_settings.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSettingsFragment())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            val selectedImage: Uri = data.data!!

            setProfilePicture(selectedImage)

            val updateInfo = UserProfileChangeRequest.Builder()
                    .setPhotoUri(selectedImage)
                    .build()

            mAuth.currentUser?.updateProfile(updateInfo)
        }
    }

    private fun setProfilePicture(imageUri: Uri?) {

        Glide.with(profile_iv_profile_photo.context)
            .asDrawable()
            .load(imageUri)
            .apply(
                RequestOptions()
                    .circleCrop()
                    .placeholder(R.color.white)
                    .error(R.drawable.bc_profile_photo))
            .into(profile_iv_profile_photo)
    }
}
