package com.example.f_feed.di

import android.os.Bundle
import com.example.f_feed.FeedFragmentView
import dagger.Component
import ru.surfstudio.android.core.mvp.configurator.ScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.ui.activity.di.ActivityComponent
import ru.surfstudio.standard.ui.activity.di.FragmentScreenConfigurator
import ru.surfstudio.standard.ui.screen.FragmentScreenModule

class FeedScreenConfigurator(bundle: Bundle) : FragmentScreenConfigurator(bundle) {

    @PerScreen
    @Component(dependencies = [ActivityComponent::class],
            modules = [FragmentScreenModule::class])
    internal interface FeedScreenComponent
        : ScreenComponent<FeedFragmentView>

    @Suppress("DEPRECATION")
    override fun createScreenComponent(
            parentComponent: ActivityComponent?,
            fragmentScreenModule: FragmentScreenModule?,
            args: Bundle?
    ): ScreenComponent<*> {
        return DaggerFeedScreenConfigurator_FeedScreenComponent.builder()
                .activityComponent(parentComponent)
                .fragmentScreenModule(fragmentScreenModule)
                .build()
    }
}