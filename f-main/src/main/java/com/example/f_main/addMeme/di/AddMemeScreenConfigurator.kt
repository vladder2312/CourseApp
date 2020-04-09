package com.example.f_main.addMeme.di

import android.os.Bundle
import com.example.f_main.addMeme.AddMemeFragmentRoute
import com.example.f_main.addMeme.AddMemeFragmentView
import com.example.f_main.addMeme.AddMemePresenter
import com.example.f_main.addMeme.loadImageDialog.LoadImageDialogComponent
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
 * Конфигуратор экрана добавления мема
 * */
class AddMemeScreenConfigurator(bundle: Bundle) : FragmentScreenConfigurator(bundle) {

    @PerScreen
    @Component(dependencies = [ActivityComponent::class],
            modules = [FragmentScreenModule::class, AddMemeScreenModule::class])
    interface AddMemeScreenComponent : BindableScreenComponent<AddMemeFragmentView>, LoadImageDialogComponent

    @Module
    internal class AddMemeScreenModule(route: AddMemeFragmentRoute) : CustomScreenModule<AddMemeFragmentRoute>(route) {

        @Provides
        @PerScreen
        internal fun providePresenter(presenter: AddMemePresenter) = Any()
    }

    @Suppress("DEPRECATION")
    override fun createScreenComponent(
            parentComponent: ActivityComponent?,
            fragmentScreenModule: FragmentScreenModule?,
            args: Bundle?
    ): ScreenComponent<*> {
        return DaggerAddMemeScreenConfigurator_AddMemeScreenComponent.builder()
                .activityComponent(parentComponent)
                .fragmentScreenModule(fragmentScreenModule)
                .addMemeScreenModule(AddMemeScreenModule(AddMemeFragmentRoute()))
                .build()
    }
}