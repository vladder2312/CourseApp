package com.example.f_main


import android.app.FragmentTransaction.TRANSIT_NONE
import com.example.f_main.feed.FeedFragmentRoute
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.core.ui.navigation.fragment.FragmentNavigator
import javax.inject.Inject

class MainPresenter @Inject constructor(
        private val bindModel: MainBindModel,
        private val fragmentNavigator: FragmentNavigator,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

        bindModel.feedClickedAction bindTo { changeFragment() }
        bindModel.addMemeClickedAction bindTo ::changeFragment
        bindModel.profileClickedAction bindTo ::changeFragment
    }

    fun changeFragment(){
        fragmentNavigator.replace(FeedFragmentRoute(),false, TRANSIT_NONE )
    }
}