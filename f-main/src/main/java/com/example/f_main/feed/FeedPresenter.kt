package com.example.f_main.feed

import com.example.f_meme.MemeActivityRoute
import com.example.i_memes.MemesInteractor
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.core.ui.navigation.activity.navigator.ActivityNavigator
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.domain.feed.Meme
import ru.surfstudio.standard.ui.navigation.ShareRoute
import ru.surfstudio.standard.ui.placeholder.LoadState
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Презентер экрана с мемами
 * */
@PerScreen
class FeedPresenter @Inject constructor(
        private val bindModel: FeedBindModel,
        private val memesInteractor: MemesInteractor,
        private val activityNavigator: ActivityNavigator,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    lateinit var memes: List<Meme>

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

        bindModel.placeholderState.accept(LoadState.TRANSPARENT_LOADING)

        bindModel.refreshFeedAction bindTo ::loadMemes
        bindModel.shareMemeAction bindTo { shareMeme(it) }
        bindModel.openMemeAction bindTo { openMeme(it) }
        bindModel.hideMemesAction bindTo { bindModel.memesState.accept(listOf()) }
        bindModel.showMemesAction bindTo { bindModel.memesState.accept(memes) }
        bindModel.filterMemesAction bindTo { filterMemes(it) }
    }

    private fun loadMemes() {
        subscribeIoHandleError(
                memesInteractor.getMemes().timeout(8, TimeUnit.SECONDS),
                {
                    memes = it
                    bindModel.memesState.accept(memes)
                    bindModel.placeholderState.accept(LoadState.NONE)
                },
                {
                    bindModel.failedLoadMemesState.accept(it.localizedMessage)
                    bindModel.placeholderState.accept(LoadState.ERROR)
                }
        )
    }

    private fun filterMemes(text: String) {
        val filteredMemes = memes.filter {
            it.title.toLowerCase(Locale.getDefault()).contains(text.trim().toLowerCase(Locale.getDefault()))
        }
        bindModel.memesState.accept(filteredMemes)
    }

    private fun openMeme(meme: Meme) = activityNavigator.start(MemeActivityRoute(meme))

    private fun shareMeme(meme: Meme) {
        activityNavigator.start(ShareRoute("${meme.title}\n${meme.description}"))
    }
}