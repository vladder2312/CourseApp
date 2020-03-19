package com.example.f_main.feed

import android.annotation.SuppressLint
import com.example.i_feed.MemesInteractor
import com.example.i_feed.MemesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.domain.feed.Meme
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FeedPresenter @Inject constructor(
        private val bindModel: FeedBindModel,
        private val memesInteractor: MemesInteractor,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

        bindModel.refreshFeedAction bindTo ::loadMemes
    }

    @SuppressLint("CheckResult")
    fun loadMemes(){
        memesInteractor.getMemes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(8, TimeUnit.SECONDS)
                .subscribe( {
                    bindModel.memesState.accept(it)
                },{
                    bindModel.failedLoadMemesState.accept(it.localizedMessage)
                })
    }
}