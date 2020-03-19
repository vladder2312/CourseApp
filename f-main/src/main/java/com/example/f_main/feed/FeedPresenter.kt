package com.example.f_main.feed

import android.annotation.SuppressLint
import com.example.i_memes.MemesInteractor
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@PerScreen
class FeedPresenter @Inject constructor(
        private val bindModel: FeedBindModel,
        private val memesInteractor: MemesInteractor,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

        bindModel.refreshFeedAction bindTo ::loadMemes
    }

    private fun loadMemes() {
        subscribeIoHandleError(
                memesInteractor.getMemes().timeout(8, TimeUnit.SECONDS),
                {
                    bindModel.memesState.accept(it)
                },
                {
                    bindModel.failedLoadMemesState.accept(it.localizedMessage)
                }
        )
    }
}