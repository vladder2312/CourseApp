package com.example.f_main.profile.exitDialog

import androidx.fragment.app.DialogFragment
import ru.surfstudio.android.mvp.dialog.navigation.route.DialogRoute

/**
 * Роут диалога выхода
 * */
class ExitDialogRoute : DialogRoute() {

    override fun getFragmentClass(): Class<out DialogFragment> = ExitDialogFragment::class.java

    override fun createFragment() = ExitDialogFragment()
}