package com.example.f_meme.di

import android.content.Intent
import com.example.f_meme.MemeActivityView
import com.example.f_meme.MemePresenter
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.mvp.configurator.BindableScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.ui.activity.di.ActivityComponent
import ru.surfstudio.standard.ui.activity.di.ActivityScreenConfigurator
import ru.surfstudio.standard.ui.navigation.MemeActivityRoute
import ru.surfstudio.standard.ui.screen.ActivityScreenModule
import ru.surfstudio.standard.ui.screen.CustomScreenModule

class MemeScreenConfigurator(intent: Intent) : ActivityScreenConfigurator(intent) {

    @PerScreen
    @Component(dependencies = [ActivityComponent::class],
            modules = [ActivityScreenModule::class, MemeScreenModule::class])
    interface MainScreenComponent
        : BindableScreenComponent<MemeActivityView>

    @Module
    internal class MemeScreenModule(route: MemeActivityRoute) : CustomScreenModule<MemeActivityRoute>(route) {

        @Provides
        @PerScreen
        fun providePresenter(presenter: MemePresenter) = Any()
    }

    @Suppress("DEPRECATION")
    override fun createScreenComponent(parentComponent: ActivityComponent,
                                       activityScreenModule: ActivityScreenModule,
                                       intent: Intent): MainScreenComponent {
        return DaggerMemeScreenConfigurator_MemeScreenComponent.builder()
                .activityComponent(parentComponent)
                .activityScreenModule(activityScreenModule)
                .memeScreenModule(MemeScreenModule(MemeActivityRoute()))
                .build()
    }
}