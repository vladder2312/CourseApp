package com.example.f_meme

import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.core.ui.navigation.activity.navigator.ActivityNavigator
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.ui.navigation.ShareRoute
import javax.inject.Inject

@PerScreen
class MemePresenter @Inject constructor(
        private val bindModel: MemeBindModel,
        private val route: MemeActivityRoute,
        private val activityNavigator: ActivityNavigator,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

        bindModel.memeState.accept(route.meme)
        bindModel.likeAction bindTo ::changeLikeState
        bindModel.shareAction bindTo ::shareMeme
    }

    private fun changeLikeState() {
        bindModel.liked = !bindModel.liked
        bindModel.likeState.accept()
    }

    private fun shareMeme() {
        activityNavigator.start(ShareRoute(route.meme.title + "\n" + route.meme.description))
    }
}