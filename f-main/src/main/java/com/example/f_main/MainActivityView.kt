package com.example.f_main

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import com.example.f_main.di.MainScreenConfigurator
import ru.surfstudio.android.core.ui.FragmentContainer
import javax.inject.Inject
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxActivityView

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
        bindModel.feedClickedAction.accept()
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_feed -> {
                bindModel.feedClickedAction.accept()
            }
            R.id.action_addMeme -> {
                bindModel.addMemeClickedAction.accept()
            }
            R.id.action_profile -> {
                bindModel.feedClickedAction.accept()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}