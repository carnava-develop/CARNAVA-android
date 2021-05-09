package com.carnava.android.user.presentation.profile

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.auth.domain.usecases.LogoutUseCase
import com.carnava.android.core.extensions.requireNavigationContextChanger
import com.carnava.android.core.navigation.Screens
import com.carnava.android.core.ui.BaseFragment
import com.carnava.android.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private lateinit var bindging: FragmentProfileBinding

    override fun setupView(view: View) {
        bindging = FragmentProfileBinding.bind(view)
        with(bindging) {
            rateAppProfileContainer.setOnClickListener {
                val packageName = requireContext().packageName
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$packageName")
                        )
                    )
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                        )
                    )
                }
            }
            shareAppProfileContainer.setOnClickListener {
                startActivity(Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "https://developer.android.com/training/sharing/")
                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION

                }, null)
            }
            logoutProfileButton.setOnClickListener {
                lifecycleScope.launch {
                    try {
                        LogoutUseCase().invoke()
                        requireNavigationContextChanger().resetNavigationContext()
                        App.navigator.reset(Screens.SignIn)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}