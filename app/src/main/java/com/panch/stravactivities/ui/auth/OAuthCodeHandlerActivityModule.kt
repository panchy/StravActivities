package com.panch.stravactivities.ui.auth

import androidx.lifecycle.ViewModelProvider
import com.panch.stravactivities.util.AuthUtils
import dagger.Module
import dagger.Provides

@Module
class OAuthCodeHandlerActivityModule {

    @Provides
    fun provideViewModel(oAuthCodeHandlerActivity: OAuthCodeHandlerActivity): OAuthCodeHandlerViewModel =
        ViewModelProvider(oAuthCodeHandlerActivity).get(OAuthCodeHandlerViewModel::class.java)

}