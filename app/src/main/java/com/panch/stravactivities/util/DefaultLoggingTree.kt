package com.panch.stravactivities.util

import com.panch.stravactivities.BuildConfig
import timber.log.Timber

class DefaultLoggingTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (BuildConfig.DEBUG)
            super.log(priority, tag, message, t)
    }
}