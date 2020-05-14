package com.panch.stravactivities.di.module

import android.app.Application
import com.panch.stravactivities.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideApp(application: Application): App {
        return application as App
    }
}