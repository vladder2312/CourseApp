package ru.surfstudio.standard.f_main

import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.State
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Модель главного экрана
 */
@PerScreen
class MainBindModel @Inject constructor() : BindModel {

    var loginValidated = false
    var passwordValidated = false

    val loginChangedAction = Action<String>()
    val passwordChangedAction = Action<String>()
    val authorizeAction = Action<Pair<String, String>>()

    val loginErrorState = State<String>()
    val passwordErrorState = State<String>()
    val authorizedState = State<Boolean>()
}