package com.panch.stravactivities.di.module

import android.content.Context
import android.content.SharedPreferences
import com.panch.stravactivities.App
import com.panch.stravactivities.data.sharedPref.UserDataProvider
import com.panch.stravactivities.util.AuthUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(app: App): SharedPreferences =
        app.getSharedPreferences("cached", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideUserDataProvider(sharedPreferences: SharedPreferences): UserDataProvider =
        UserDataProvider(sharedPreferences)

    @Singleton
    @Provides
    fun provideAuthUtils(): AuthUtils = AuthUtils()
}