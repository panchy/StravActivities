package com.panch.stravactivities.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.panch.stravactivities.App
import com.panch.stravactivities.data.sharedPref.UserDataProvider
import javax.inject.Inject

class AuthUtils {
    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var userData: UserDataProvider

    /**
     * Redirects user to Strava app to begin login flow.
     */
    fun launchStravaOAuth(activity: Activity) {
        val intentUri = Uri.parse("https://www.strava.com/oauth/mobile/authorize")
            .buildUpon()
            .appendQueryParameter("client_id", "47866")
            .appendQueryParameter("redirect_uri", "http://127.0.0.1")
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("approval_prompt", "auto")
            .appendQueryParameter("scope", "activity:read_all,activity:write")
            .build()
        val intent = Intent(Intent.ACTION_VIEW, intentUri)
        activity.startActivity(intent)
    }

    val isTokenValid: Boolean get() = userData.stravaAuth != null && userData.stravaAuth!!.expires_at > (System.currentTimeMillis() / 1000)

    val refreshToken: String? get() = userData.stravaAuth?.refreshToken

    val accessToken: String? get() = userData.stravaAuth?.accessToken

    val tokenType: String? get() = userData.stravaAuth?.tokenType
}