package com.example.f_main.feed

import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.State
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.core.mvp.loadstate.LoadStateInterface
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.domain.feed.Meme
import javax.inject.Inject

@PerScreen
class FeedBindModel @Inject constructor() : BindModel {

    val refreshFeedAction = Action<Unit>()

    val memesState = State<List<Meme>>(listOf())
    val failedLoadMemesState = State<String>()
    val placeholderState = State<LoadStateInterface>()
}