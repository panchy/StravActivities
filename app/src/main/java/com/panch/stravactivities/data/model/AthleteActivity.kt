package com.panch.stravactivities.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "activities")
data class AthleteActivity(
    @PrimaryKey val id: Long,
    val name: String?,
    val type: String?,
    val distance: Float?,
    @SerializedName("elapsed_time") val elapsedTime: Int?,
    @SerializedName("start_date_local") val startTime: String?,
    val commute: Boolean?
)