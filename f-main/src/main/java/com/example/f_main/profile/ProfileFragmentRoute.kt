package com.example.f_main.profile

import androidx.fragment.app.Fragment
import ru.surfstudio.android.core.ui.navigation.fragment.route.FragmentRoute

/**
 * Роут экрана профиля
 * */
class ProfileFragmentRoute : FragmentRoute() {

    override fun getFragmentClass(): Class<out Fragment> = ProfileFragmentView::class.java
}