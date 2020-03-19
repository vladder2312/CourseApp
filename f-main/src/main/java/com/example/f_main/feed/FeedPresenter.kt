package com.example.f_main.feed

import android.annotation.SuppressLint
import com.example.i_memes.MemesInteractor
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.ui.placeholder.LoadState
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

        bindModel.placeholderState.accept(LoadState.TRANSPARENT_LOADING)

        bindModel.refreshFeedAction bindTo ::loadMemes
    }

    private fun loadMemes() {
        subscribeIoHandleError(
                memesInteractor.getMemes().timeout(8, TimeUnit.SECONDS),
                {
                    bindModel.memesState.accept(it)
                    bindModel.placeholderState.accept(LoadState.NONE)
                },
                {
                    bindModel.failedLoadMemesState.accept(it.localizedMessage)
                    bindModel.placeholderState.accept(LoadState.ERROR)
                }
        )
    }
}