package com.example.f_main.feed.di

import android.os.Bundle
import com.example.f_main.feed.FeedFragmentRoute
import com.example.f_main.feed.FeedFragmentView
import com.example.f_main.feed.FeedPresenter
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.mvp.configurator.BindableScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.ui.activity.di.ActivityComponent
import ru.surfstudio.standard.ui.activity.di.FragmentScreenConfigurator
import ru.surfstudio.standard.ui.screen.CustomScreenModule
import ru.surfstudio.standard.ui.screen.FragmentScreenModule

class FeedScreenConfigurator(bundle: Bundle) : FragmentScreenConfigurator(bundle) {

    @PerScreen
    @Component(
            dependencies = [ActivityComponent::class],
            modules = [FragmentScreenModule::class, FeedScreenModule::class]
    )
    interface FeedScreenComponent : BindableScreenComponent<FeedFragmentView>

    @Module
    internal class FeedScreenModule(route: FeedFragmentRoute) :
            CustomScreenModule<FeedFragmentRoute>(route) {

        @Provides
        @PerScreen
        internal fun providePresenter(presenter: FeedPresenter) = Any()
    }

    @Suppress("DEPRECATION")
    override fun createScreenComponent(
            parentComponent: ActivityComponent,
            fragmentScreenModule: FragmentScreenModule,
            args: Bundle
    ): FeedScreenComponent {
        return DaggerFeedScreenConfigurator_FeedScreenComponent.builder()
                .activityComponent(parentComponent)
                .fragmentScreenModule(fragmentScreenModule)
                .feedScreenModule(FeedScreenModule(FeedFragmentRoute()))
                .build()
    }
}