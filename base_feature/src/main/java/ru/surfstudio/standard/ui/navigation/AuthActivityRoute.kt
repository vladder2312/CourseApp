package ru.surfstudio.standard.ui.navigation

import ru.surfstudio.android.core.ui.navigation.feature.route.feature.ActivityCrossFeatureRoute

class AuthActivityRoute : ActivityCrossFeatureRoute() {

    override fun targetClassPath(): String {
        return "ru.surfstudio.standard.f_authorization.AuthActivityView"
    }
}