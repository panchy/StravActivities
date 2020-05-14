package com.panch.stravactivities.di.module

import android.app.Application
import androidx.room.Room
import com.panch.stravactivities.data.db.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "app.db").build()
    }
}