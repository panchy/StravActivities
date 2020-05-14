package com.panch.stravactivities

import com.panch.stravactivities.di.component.AppComponent
import com.panch.stravactivities.di.component.DaggerAppComponent
import com.panch.stravactivities.di.component.NetworkComponent
import com.panch.stravactivities.util.DefaultLoggingTree
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber

class App : DaggerApplication() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var networkComponent: NetworkComponent
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DefaultLoggingTree())
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath(getString(R.string.font_roboto_regular))
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder()
            .application(this@App)
            .build()
        networkComponent = appComponent.networkComponent()
        return appComponent
    }

}