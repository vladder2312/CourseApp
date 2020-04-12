package com.example.f_main.profile

import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.State
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.domain.feed.Meme
import javax.inject.Inject

/**
 * Бинд модель экрана профиля
 * */
@PerScreen
class ProfileBindModel @Inject constructor() : BindModel {

    val openExitDialog = Action<Unit>()
    val openMemeAction = Action<Meme>()
    val shareMemeAction = Action<Meme>()
    val refreshAction = Action<Unit>()

    val userDataState = State<Pair<String, String>>()
    val memesState = State<List<Meme>>()
}