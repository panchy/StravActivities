package com.panch.stravactivities.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.panch.stravactivities.base.BaseViewModel
import com.panch.stravactivities.base.DataWrapper
import com.panch.stravactivities.data.clientId
import com.panch.stravactivities.data.clientSecret
import com.panch.stravactivities.data.model.StravaAuth
import com.panch.stravactivities.util.DataWrapperUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OAuthCodeHandlerViewModel : BaseViewModel() {

    private val _stravaAuthToken = MutableLiveData<DataWrapper<StravaAuth>>()
    val stravaAuth: LiveData<DataWrapper<StravaAuth>>
        get() = _stravaAuthToken

    private val dataWrapperUtils = DataWrapperUtils<StravaAuth>()

    fun getInitialToken(code: String) {
        _stravaAuthToken.value = DataWrapper(null, true)
        val disposable = apiHelper.initialToken(code)
            .subscribe({ r ->
                _stravaAuthToken.value = dataWrapperUtils.convertResponseToDataWrapper(r)
            }, { t: Throwable? ->
                _stravaAuthToken.value = dataWrapperUtils.convertThrowableToDataWrapper(t)
            })
        compositeDisposable.add(disposable)
    }

    fun getNextToken(refreshToken: String) {
        _stravaAuthToken.value = DataWrapper(null, true)
        val disposable = apiHelper.refreshToken(refreshToken)
            .subscribe({ r ->
                _stravaAuthToken.value = dataWrapperUtils.convertResponseToDataWrapper(r)
            }, { t: Throwable? ->
                _stravaAuthToken.value = dataWrapperUtils.convertThrowableToDataWrapper(t)
            })
        compositeDisposable.add(disposable)
    }

}