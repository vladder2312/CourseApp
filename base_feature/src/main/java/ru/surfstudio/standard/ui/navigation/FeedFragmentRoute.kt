package ru.surfstudio.standard.ui.navigation

import androidx.fragment.app.Fragment
import ru.surfstudio.android.core.ui.navigation.feature.route.feature.FragmentCrossFeatureRoute
import ru.surfstudio.android.core.ui.navigation.fragment.route.FragmentRoute

class FeedFragmentRoute : FragmentCrossFeatureRoute() {

    override fun targetClassPath(): String {
        return "com.example.f_feed.FeedFragmentView"
    }
}