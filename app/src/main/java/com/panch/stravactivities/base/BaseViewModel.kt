package com.panch.stravactivities.base

import androidx.lifecycle.ViewModel
import com.panch.stravactivities.App
import com.panch.stravactivities.data.db.AppDatabase
import com.panch.stravactivities.data.rest.ApiHelper
import com.panch.stravactivities.data.rest.RestApi
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    init {
        App.networkComponent.inject(this)
        Timber.e("Created ${javaClass.simpleName}")
    }

    @Inject
    lateinit var apiHelper: ApiHelper

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}