package ru.surfstudio.standard.ui.navigation

import ru.surfstudio.android.core.ui.navigation.feature.route.feature.FragmentCrossFeatureRoute

class ProfileFragmentRoute : FragmentCrossFeatureRoute() {

    override fun targetClassPath(): String {
        return "com.example.f_profile.ProfileFragmentView"
    }
}