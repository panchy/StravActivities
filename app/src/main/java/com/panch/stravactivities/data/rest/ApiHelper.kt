package com.panch.stravactivities.data.rest

import com.panch.stravactivities.data.clientId
import com.panch.stravactivities.data.clientSecret
import com.panch.stravactivities.data.db.AppDatabase
import com.panch.stravactivities.util.AuthUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiHelper(
    private val restClient: RestApi,
    private val authUtils: AuthUtils,
    val appDatabase: AppDatabase
) {

    fun initialToken(code: String) =
        restClient.getToken(clientId, clientSecret, code, "authorization_code")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun refreshToken(refreshToken: String) =
        restClient.getTokenWithRefreshToken(clientId, clientSecret, refreshToken, "refresh_token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    val athleteActivities = restClient.activities.subscribeOn(Schedulers.io())
        .map {
            if (it.isSuccessful && it.body() != null)
                appDatabase.athleteActivitiesDao().insertActivities(it.body()!!)
            return@map it
        }
        .observeOn(AndroidSchedulers.mainThread())

}