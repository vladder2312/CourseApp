package com.example.f_meme

import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

@PerScreen
class MemePresenter @Inject constructor(
        private val bindModel: MemeBindModel,
        private val route: MemeActivityRoute,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

        bindModel.memeState.accept(route.meme)
        bindModel.likeClickedAction bindTo { changeLikeState() }
    }

    private fun changeLikeState() {
        bindModel.liked = !bindModel.liked
        bindModel.likeState.accept()
    }
}