package com.panch.stravactivities.ui.main.listFragment

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

@Module
class AthleteActivitiesListFragmentModule {
    @Provides
    fun provideAthleteActivitiyListViewModel(fragmentAthleteActivities: AthleteActivitiesListFragment): AthleteActivitiesListViewModel =
        ViewModelProvider(fragmentAthleteActivities).get(AthleteActivitiesListViewModel::class.java)
}