package com.example.f_main.addMeme

import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.State
import ru.surfstudio.android.core.mvp.model.ScreenModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Бинд модель экрана добавления мема
 * */
@PerScreen
class AddMemeBindModel @Inject constructor() : ScreenModel() {

    val loadImageAction = Action<Unit>()
    val createMemeAction = Action<Unit>()

    val imageState = State<String>()
    val openCamera = State<Unit>()
}