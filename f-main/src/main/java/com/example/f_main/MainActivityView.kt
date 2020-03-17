package com.example.f_main

import android.os.Bundle
import android.os.PersistableBundle
import com.example.f_main.di.MainScreenConfigurator
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxActivityView
import ru.surfstudio.android.core.mvp.configurator.BaseActivityViewConfigurator
import javax.inject.Inject

class MainActivityView : BaseRxActivityView() {

    @Inject
    lateinit var bindModel: MainBindModel

    @Inject
    lateinit var presenter: MainPresenter

    override fun createConfigurator() = MainScreenConfigurator(intent)

    override fun getContentView(): Int = R.layout.activity_main

    override fun getScreenName() = "MainActivityView"

    override fun onCreate(
            savedInstanceState: Bundle?,
            persistentState: PersistableBundle?,
            viewRecreated: Boolean
    ) {
        bind()
    }

    fun bind(){

    }
}