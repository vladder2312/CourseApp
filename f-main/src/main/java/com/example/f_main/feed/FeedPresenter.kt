package com.example.f_main.feed

import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.domain.feed.Meme
import javax.inject.Inject

class FeedPresenter @Inject constructor(
        private val bindModel: FeedBindModel,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

        bindModel.memesState.accept(listOf(

        ))
    }
}