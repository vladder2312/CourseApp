package com.example.f_meme

import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.State
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.domain.feed.Meme
import javax.inject.Inject

@PerScreen
class MemeBindModel @Inject constructor() : BindModel {

    var liked = false

    val shareAction = Action<Unit>()
    val likeAction = Action<Unit>()

    val likeState = State<Unit>()
    val memeState = State<Meme>()
}