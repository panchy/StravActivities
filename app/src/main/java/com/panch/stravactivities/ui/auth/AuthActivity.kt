package com.panch.stravactivities.ui.auth

import android.content.Intent
import com.panch.stravactivities.R
import com.panch.stravactivities.base.BaseActivityWithDI
import com.panch.stravactivities.data.currentAccessToken
import com.panch.stravactivities.data.currentTokenType
import com.panch.stravactivities.ui.main.MainActivity
import com.panch.stravactivities.util.AuthUtils
import kotlinx.android.synthetic.main.activity_auth.*
import timber.log.Timber
import javax.inject.Inject

class AuthActivity : BaseActivityWithDI() {
    override val layoutRes: Int = R.layout.activity_auth

    @Inject
    lateinit var authUtils: AuthUtils

    override fun onCreated() {
        overridePendingTransition(0, 0)
        btnLogin.setOnClickListener {
            authUtils.launchStravaOAuth(this)
        }
    }

    override fun onResume() {
        super.onResume()
        if (authUtils.isTokenValid) {
            currentTokenType = authUtils.tokenType
            currentAccessToken = authUtils.accessToken
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else if (authUtils.refreshToken != null) {
            startActivity(Intent(this, OAuthCodeHandlerActivity::class.java))
            //Do not finish this activity because onResume will be used to launch main activity after retrieving access_token
        }
    }
}
