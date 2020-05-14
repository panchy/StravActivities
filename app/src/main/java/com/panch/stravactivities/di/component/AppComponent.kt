package com.panch.stravactivities.di.component

import android.app.Application
import com.panch.stravactivities.di.module.ActivityBinder
import com.panch.stravactivities.di.module.AppModule
import com.panch.stravactivities.di.module.DatabaseModule
import com.panch.stravactivities.di.module.SharedPreferencesModule
import com.panch.stravactivities.util.AuthUtils
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBinder::class,
        AppModule::class,
        DatabaseModule::class,
        SharedPreferencesModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun networkComponent(): NetworkComponent
    fun inject(target: AuthUtils)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}