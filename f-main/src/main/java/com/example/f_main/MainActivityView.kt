package com.example.f_main

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import com.example.f_main.di.MainScreenConfigurator
import kotlinx.android.synthetic.main.activity_main.*
import ru.surfstudio.android.core.ui.FragmentContainer
import javax.inject.Inject
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxActivityView

/**
 * Главный экран
 * */
class MainActivityView : BaseRxActivityView(), FragmentContainer {

    @Inject
    lateinit var bindModel: MainBindModel

    @Inject
    lateinit var presenter: MainPresenter

    @LayoutRes
    override fun getContentView(): Int = R.layout.activity_main
    override fun getContentContainerViewId() = R.id.fragmentHolder
    override fun createConfigurator() = MainScreenConfigurator(intent)
    override fun getScreenName() = "MainActivityView"
    override fun onCreate(
            savedInstanceState: Bundle?,
            persistentState: PersistableBundle?,
            viewRecreated: Boolean
    ) {
        bindModel.showFeedAction.accept()
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_feed -> {
                    bindModel.showFeedAction.accept()
                }
                R.id.action_addMeme -> {
                    bindModel.showAddMemeAction.accept()
                }
                R.id.action_profile -> {
                    bindModel.showProfileAction.accept()
                }
            }
            true
        }
    }
}