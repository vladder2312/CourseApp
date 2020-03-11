package ru.surfstudio.standard.f_main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxActivityView
import ru.surfstudio.android.template.f_main.R
import ru.surfstudio.standard.f_main.di.MainScreenConfigurator
import javax.inject.Inject

/**
 * Вью главного экрана
 */
class MainActivityView : BaseRxActivityView() {

    @Inject
    lateinit var bindModel: MainBindModel

    @Inject
    lateinit var presenter: MainPresenter

    override fun createConfigurator() = MainScreenConfigurator(intent)

    override fun getScreenName(): String = "MainActivityView"

    @LayoutRes
    override fun getContentView(): Int = R.layout.activity_main

    override fun onCreate(
            savedInstanceState: Bundle?,
            persistentState: PersistableBundle?,
            viewRecreated: Boolean
    ) {

    }
}
