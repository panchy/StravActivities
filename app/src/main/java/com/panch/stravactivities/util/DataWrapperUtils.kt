package com.panch.stravactivities.util

import com.panch.stravactivities.base.DataWrapper
import retrofit2.Response

class DataWrapperUtils<T> {
    fun convertResponseToDataWrapper(response: Response<T>): DataWrapper<T> {
        return if (response.isSuccessful) {
            DataWrapper(response.body(), false)
        } else {
            DataWrapper(null, false)
        }
    }

    fun convertThrowableToDataWrapper(throwable: Throwable?): DataWrapper<T> {
        return DataWrapper(null, false)
    }
}

