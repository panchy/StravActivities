package com.panch.stravactivities.data.db

import androidx.room.*
import com.panch.stravactivities.data.model.AthleteActivity

@Dao
interface AthleteActivitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActivities(users: List<AthleteActivity>)

    @Update
    fun updateActivities(users: List<AthleteActivity>)

    @Query("SELECT * from activities")
    fun getActivities(): Array<AthleteActivity>
}