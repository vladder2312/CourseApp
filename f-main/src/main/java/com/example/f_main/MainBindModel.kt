package com.example.f_main

import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Бинд модель главного экрана
 * */
@PerScreen
class MainBindModel @Inject constructor() : BindModel {

    val showFeedAction = Action<Unit>()
    val showAddMemeAction = Action<Unit>()
    val showProfileAction = Action<Unit>()
}