package com.example.f_main.addMeme.loadImageDialog

import androidx.fragment.app.DialogFragment
import ru.surfstudio.android.mvp.dialog.navigation.route.DialogRoute

class LoadImageDialogRoute : DialogRoute() {

    override fun getFragmentClass(): Class<out DialogFragment> = LoadImageDialogFragment::class.java

    override fun createFragment() = LoadImageDialogFragment()
}