package com.panch.stravactivities.di.module

import com.panch.stravactivities.ui.auth.AuthActivity
import com.panch.stravactivities.ui.auth.AuthActivityModule
import com.panch.stravactivities.ui.auth.OAuthCodeHandlerActivity
import com.panch.stravactivities.ui.auth.OAuthCodeHandlerActivityModule
import com.panch.stravactivities.ui.main.MainActivity
import com.panch.stravactivities.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinder {
    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainActivityFragmentsBinder::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [AuthActivityModule::class])
    abstract fun bindAuthActivity(): AuthActivity

    @ContributesAndroidInjector(modules = [OAuthCodeHandlerActivityModule::class])
    abstract fun bindOAuthCodeHandlerActivity(): OAuthCodeHandlerActivity
}