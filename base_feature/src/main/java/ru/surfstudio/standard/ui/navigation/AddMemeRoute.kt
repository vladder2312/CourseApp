package ru.surfstudio.standard.ui.navigation

import ru.surfstudio.android.core.ui.navigation.feature.route.feature.FragmentCrossFeatureRoute

class AddMemeRoute : FragmentCrossFeatureRoute() {

    override fun targetClassPath(): String {
        return "com.example.f_addMeme.AddMemeFragmentView"
    }
}