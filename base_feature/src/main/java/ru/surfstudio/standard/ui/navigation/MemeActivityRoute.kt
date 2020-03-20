package ru.surfstudio.standard.ui.navigation

import ru.surfstudio.android.core.ui.navigation.feature.route.feature.ActivityCrossFeatureRoute

class MemeActivityRoute : ActivityCrossFeatureRoute() {

    override fun targetClassPath(): String {
        return "com.example.f_meme.MemeActivityView"
    }
}