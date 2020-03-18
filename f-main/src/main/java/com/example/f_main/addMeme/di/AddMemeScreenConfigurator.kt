package com.example.f_main.addMeme.di

import android.os.Bundle
import dagger.Component
import ru.surfstudio.android.core.mvp.configurator.ScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.ui.activity.di.ActivityComponent
import ru.surfstudio.standard.ui.activity.di.FragmentScreenConfigurator
import ru.surfstudio.standard.ui.screen.FragmentScreenModule

class AddMemeScreenConfigurator(bundle: Bundle) : FragmentScreenConfigurator(bundle) {

    @PerScreen
    @Component(dependencies = [ActivityComponent::class],
            modules = [FragmentScreenModule::class])
    internal interface AddMemeScreenComponent
        : ScreenComponent<com.example.f_main.addMeme.AddMemeFragmentView>

    @Suppress("DEPRECATION")
    override fun createScreenComponent(
            parentComponent: ActivityComponent?,
            fragmentScreenModule: FragmentScreenModule?,
            args: Bundle?
    ): ScreenComponent<*> {
        return DaggerAddMemeScreenConfigurator_AddMemeScreenComponent.builder()
                .activityComponent(parentComponent)
                .fragmentScreenModule(fragmentScreenModule)
                .build()
    }
}