package com.example.f_main.profile

import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.State
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Модель экрана профиля
 * */
@PerScreen
class ProfileBindModel @Inject constructor() : BindModel {

    val openExitDialog = Action<Unit>()

    val userDataState = State<Pair<String, String>>()
}