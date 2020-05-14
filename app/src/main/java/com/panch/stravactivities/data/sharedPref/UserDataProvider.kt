package com.panch.stravactivities.data.sharedPref

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.panch.stravactivities.data.model.StravaAuth

class UserDataProvider(private val sharedPreferences: SharedPreferences) {
    var stravaAuth: StravaAuth?
        get() {
            val token = sharedPreferences.getString("strava_auth", "")!!
            return if (token.isEmpty()) {
                null
            } else {
                Gson().fromJson(token, StravaAuth::class.java)
            }
        }
        set(value) = sharedPreferences.edit {
            putString("strava_auth", Gson().toJson(value))
        }

}


