package com.example.f_main.di

import android.content.Intent
import com.example.f_main.MainActivityView
import com.example.f_main.MainPresenter
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.mvp.configurator.BindableScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.ui.activity.di.ActivityComponent
import ru.surfstudio.standard.ui.activity.di.ActivityScreenConfigurator
import ru.surfstudio.standard.ui.navigation.MainActivityRoute
import ru.surfstudio.standard.ui.screen.ActivityScreenModule
import ru.surfstudio.standard.ui.screen.CustomScreenModule

/**
 * Конфигуратор главного экрана
 * */
class MainScreenConfigurator(intent: Intent) : ActivityScreenConfigurator(intent) {

    @PerScreen
    @Component(dependencies = [ActivityComponent::class],
            modules = [ActivityScreenModule::class, MainScreenModule::class])
    interface MainScreenComponent
        : BindableScreenComponent<MainActivityView>

    @Module
    internal class MainScreenModule(route: MainActivityRoute) : CustomScreenModule<MainActivityRoute>(route) {

        @Provides
        @PerScreen
        fun providePresenter(presenter: MainPresenter) = Any()
    }

    @Suppress("DEPRECATION")
    override fun createScreenComponent(parentComponent: ActivityComponent,
                                       activityScreenModule: ActivityScreenModule,
                                       intent: Intent): MainScreenComponent {
        return DaggerMainScreenConfigurator_MainScreenComponent.builder()
                .activityComponent(parentComponent)
                .activityScreenModule(activityScreenModule)
                .mainScreenModule(MainScreenModule(MainActivityRoute()))
                .build()
    }
}