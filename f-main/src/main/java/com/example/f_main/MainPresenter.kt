package com.example.f_main

import android.app.FragmentTransaction.TRANSIT_NONE
import com.example.f_main.addMeme.AddMemeFragmentRoute
import com.example.f_main.feed.FeedFragmentRoute
import com.example.f_main.profile.ProfileFragmentRoute
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.core.ui.navigation.fragment.FragmentNavigator
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

@PerScreen
class MainPresenter @Inject constructor(
        private val bindModel: MainBindModel,
        private val fragmentNavigator: FragmentNavigator,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

        bindModel.showFeedAction bindTo ::changeToFeedFragment
        bindModel.showAddMemeAction bindTo ::changeToAddMemeFragment
        bindModel.showProfileAction bindTo ::changeToProfileFragment
    }

    private fun changeToFeedFragment(){
        fragmentNavigator.replace(FeedFragmentRoute(),false, TRANSIT_NONE )
    }

    private fun changeToAddMemeFragment(){
        fragmentNavigator.replace(AddMemeFragmentRoute(), false, TRANSIT_NONE)
    }

    private fun changeToProfileFragment(){
        fragmentNavigator.replace(ProfileFragmentRoute(), false, TRANSIT_NONE)
    }
}