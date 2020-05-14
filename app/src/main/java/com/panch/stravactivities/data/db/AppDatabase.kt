package com.panch.stravactivities.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.panch.stravactivities.data.model.AthleteActivity

@Database(version = 1, entities = [AthleteActivity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun athleteActivitiesDao(): AthleteActivitiesDao
}