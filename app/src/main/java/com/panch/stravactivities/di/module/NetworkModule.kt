package com.panch.stravactivities.di.module


import com.panch.stravactivities.data.db.AppDatabase
import com.panch.stravactivities.data.rest.ApiHelper
import com.panch.stravactivities.data.rest.RestApi
import com.panch.stravactivities.data.rest.createRestClient
import com.panch.stravactivities.di.scope.NetworkScope
import com.panch.stravactivities.util.AuthUtils
import dagger.Module
import dagger.Provides


@Module
class NetworkModule {
    @NetworkScope
    @Provides
    fun provideRestClient(): RestApi = createRestClient()

    @NetworkScope
    @Provides
    fun provideApiHelper(restApi: RestApi, authUtils: AuthUtils, appDatabase: AppDatabase): ApiHelper =
        ApiHelper(restApi, authUtils, appDatabase)
}