package com.panch.stravactivities.di.component

import com.panch.stravactivities.base.BaseViewModel
import com.panch.stravactivities.di.module.NetworkModule
import com.panch.stravactivities.di.scope.NetworkScope
import dagger.Subcomponent

@NetworkScope
@Subcomponent(
    modules = [NetworkModule::class]
)
interface NetworkComponent {
    fun inject(target: BaseViewModel)
}