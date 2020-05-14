package com.panch.stravactivities.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

fun Float.toDistanceString(): String {
    if (this >= 1000) {
        return String.format("%.1f km", this / 1000.0f)
    }

    return String.format("%.2f m", this)
}

fun Int.toTimeString(): String {
    val m: Int = this / 60 % 60
    val h: Int = this / (60 * 60) % 24
    var stringToReturn = ""
    if (h.toString().length == 1)
        stringToReturn += "0"
    stringToReturn += "$h:"
    if (m.toString().length == 1)
        stringToReturn += "0"
    stringToReturn += "$m"
    return stringToReturn
}

@SuppressLint("SimpleDateFormat")
fun String.toFormattedDate(): String {
    //2020-05-13T15:20:30Z
    val apiFormat = "yyyy-MM-dd'T'HH:mm:ss"
    val returnFormat = "dd.MM.yyyy HH:mm"
    val date = SimpleDateFormat(apiFormat).parse(this.replace("Z", ""))
    return SimpleDateFormat(returnFormat).format(date)
}


fun String.toDate(): Date {
    //2020-05-13T15:20:30Z
    val apiFormat = "yyyy-MM-dd'T'HH:mm:ss"
    val date = SimpleDateFormat(apiFormat).parse(this.replace("Z", ""))
    return date
}