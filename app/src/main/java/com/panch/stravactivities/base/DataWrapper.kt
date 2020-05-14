package com.panch.stravactivities.base

data class DataWrapper<T>(
    val data: T? = null,
    val loading: Boolean = true,
    val offline: Boolean = false
)