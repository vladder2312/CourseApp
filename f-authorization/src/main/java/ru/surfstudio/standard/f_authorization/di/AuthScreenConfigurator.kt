package ru.surfstudio.standard.f_authorization.di

import android.content.Intent
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.mvp.configurator.BindableScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.f_authorization.AuthActivityView
import ru.surfstudio.standard.f_authorization.AuthPresenter
import ru.surfstudio.standard.ui.activity.di.ActivityComponent
import ru.surfstudio.standard.ui.activity.di.ActivityScreenConfigurator
import ru.surfstudio.standard.ui.navigation.AuthActivityRoute
import ru.surfstudio.standard.ui.screen.ActivityScreenModule
import ru.surfstudio.standard.ui.screen.CustomScreenModule

/**
 * Конфигуратор экрана авторизации
 */
class AuthScreenConfigurator(intent: Intent) : ActivityScreenConfigurator(intent) {

    @PerScreen
    @Component(dependencies = [ActivityComponent::class],
            modules = [ActivityScreenModule::class, AuthScreenModule::class])
    interface AuthScreenComponent
        : BindableScreenComponent<AuthActivityView>

    @Module
    internal class AuthScreenModule(route: AuthActivityRoute) : CustomScreenModule<AuthActivityRoute>(route) {

        @Provides
        @PerScreen
        fun providePresenter(presenter: AuthPresenter) = Any()
    }

    @Suppress("DEPRECATION")
    override fun createScreenComponent(parentComponent: ActivityComponent,
                                       activityScreenModule: ActivityScreenModule,
                                       intent: Intent): AuthScreenComponent {
        return DaggerAuthScreenConfigurator_AuthScreenComponent.builder()
                .activityComponent(parentComponent)
                .activityScreenModule(activityScreenModule)
                .authScreenModule(AuthScreenModule(AuthActivityRoute()))
                .build()
    }
}