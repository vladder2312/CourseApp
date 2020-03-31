package com.example.f_main.profile.di

import android.os.Bundle
import com.example.f_main.profile.ProfileFragmentRoute
import com.example.f_main.profile.ProfileFragmentView
import com.example.f_main.profile.ProfilePresenter
import com.example.f_main.profile.exitDialog.ExitDialogComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.mvp.configurator.BindableScreenComponent
import ru.surfstudio.android.core.mvp.configurator.ScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.ui.activity.di.ActivityComponent
import ru.surfstudio.standard.ui.activity.di.FragmentScreenConfigurator
import ru.surfstudio.standard.ui.screen.CustomScreenModule
import ru.surfstudio.standard.ui.screen.FragmentScreenModule

/**
 * Конфигуратор экрана профиля
 * */
class ProfileScreenConfigurator(bundle: Bundle) : FragmentScreenConfigurator(bundle) {

    @PerScreen
    @Component(
            dependencies = [ActivityComponent::class],
            modules = [FragmentScreenModule::class, ProfileScreenModule::class]
    )
    interface ProfileScreenComponent : BindableScreenComponent<ProfileFragmentView>, ExitDialogComponent

    @Module
    internal class ProfileScreenModule(route: ProfileFragmentRoute) :
            CustomScreenModule<ProfileFragmentRoute>(route) {

        @Provides
        @PerScreen
        internal fun providePresenter(presenter: ProfilePresenter) = Any()
    }

    @Suppress("DEPRECATION")
    override fun createScreenComponent(
            parentComponent: ActivityComponent,
            fragmentScreenModule: FragmentScreenModule,
            args: Bundle
    ): ScreenComponent<*> {
        return DaggerProfileScreenConfigurator_ProfileScreenComponent.builder()
                .activityComponent(parentComponent)
                .fragmentScreenModule(fragmentScreenModule)
                .profileScreenModule(ProfileScreenModule(ProfileFragmentRoute()))
                .build()
    }
}