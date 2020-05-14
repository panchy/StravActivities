package com.panch.stravactivities.ui.auth

import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.Observer
import com.panch.stravactivities.R
import com.panch.stravactivities.base.BaseActivityWithDI
import com.panch.stravactivities.base.DataWrapper
import com.panch.stravactivities.data.model.StravaAuth
import com.panch.stravactivities.data.sharedPref.UserDataProvider
import com.panch.stravactivities.util.AuthUtils
import com.panch.stravactivities.util.hide
import com.panch.stravactivities.util.show
import kotlinx.android.synthetic.main.layout_loading.*
import javax.inject.Inject

class OAuthCodeHandlerActivity : BaseActivityWithDI() {
    override val layoutRes: Int = R.layout.activity_o_auth_code_handler

    @Inject
    lateinit var viewModel: OAuthCodeHandlerViewModel

    @Inject
    lateinit var userData: UserDataProvider

    @Inject
    lateinit var authUtils: AuthUtils

    private val authCodeObserver = Observer<DataWrapper<StravaAuth>> {
        if (it.loading) {
            layout_loading.show()
            return@Observer
        }
        if (it.data == null) {
            Toast.makeText(this, "An error occurred.", Toast.LENGTH_SHORT).show()
            finish()
            return@Observer
        }
        userData.stravaAuth = it.data
        layout_loading.hide()
        finish()
    }

    override fun onCreated() {
        viewModel.stravaAuth.observe(this, authCodeObserver)
        if (intent?.data != null) {
            val data: Uri = intent.data
            val authCode = data.getQueryParameter("code")
            viewModel.getInitialToken(authCode)
        }
        //If we have a refresh token, just get the next token.
        val refreshToken = authUtils.refreshToken
        if (refreshToken != null && refreshToken.isNotEmpty() && !authUtils.isTokenValid) {
            viewModel.getNextToken(refreshToken)
            return
        }
    }
}
