package com.example.f_main.feed

import androidx.fragment.app.Fragment
import ru.surfstudio.android.core.ui.navigation.fragment.route.FragmentRoute

/**
 * Роут экрана с мемами
 * */
class FeedFragmentRoute : FragmentRoute() {

    override fun getFragmentClass(): Class<out Fragment> = FeedFragmentView::class.java
}