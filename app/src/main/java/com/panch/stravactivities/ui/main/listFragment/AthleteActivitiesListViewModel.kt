package com.panch.stravactivities.ui.main.listFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.panch.stravactivities.base.BaseViewModel
import com.panch.stravactivities.base.DataWrapper
import com.panch.stravactivities.data.model.AthleteActivity
import com.panch.stravactivities.util.DataWrapperUtils
import com.panch.stravactivities.util.toDate
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AthleteActivitiesListViewModel : BaseViewModel() {

    private val _activities = MutableLiveData<DataWrapper<List<AthleteActivity>>>()
    val activities: LiveData<DataWrapper<List<AthleteActivity>>>
        get() = _activities

    private val dataWrapperUtils = DataWrapperUtils<List<AthleteActivity>>()

    fun loadActivities() {
        _activities.value = DataWrapper()
        val disposable = apiHelper.athleteActivities
            .subscribe({
                _activities.value = dataWrapperUtils.convertResponseToDataWrapper(it)
            }, {
                tryToGetActivitiesFromLocalDatabase()
            })
        compositeDisposable.add(disposable)
    }

    private fun tryToGetActivitiesFromLocalDatabase() {
        val disposable = Single.fromCallable {
            val offlineActivities = apiHelper.appDatabase.athleteActivitiesDao().getActivities()
            return@fromCallable offlineActivities.toMutableList().sortedBy {
                it.startTime!!.toDate()
            }.reversed()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isNotEmpty()) {
                    _activities.value =
                        DataWrapper(it, loading = false, offline = true)
                } else {
                    _activities.value = DataWrapper(loading = false)
                }
            }, {
                _activities.value = dataWrapperUtils.convertThrowableToDataWrapper(it)
            })
        compositeDisposable.add(disposable)
    }
}