package com.panch.stravactivities.di.module

import com.panch.stravactivities.ui.main.listFragment.AthleteActivitiesListFragment
import com.panch.stravactivities.ui.main.listFragment.AthleteActivitiesListFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsBinder {

    @ContributesAndroidInjector(modules = [AthleteActivitiesListFragmentModule::class])
    abstract fun bindListFragment(): AthleteActivitiesListFragment

}