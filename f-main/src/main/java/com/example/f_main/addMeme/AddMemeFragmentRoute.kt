package com.example.f_main.addMeme

import androidx.fragment.app.Fragment
import ru.surfstudio.android.core.ui.navigation.fragment.route.FragmentRoute

/**
 * Роут экрана добаления мема
 * */
class AddMemeFragmentRoute : FragmentRoute() {

    override fun getFragmentClass(): Class<out Fragment> = AddMemeFragmentView::class.java
}