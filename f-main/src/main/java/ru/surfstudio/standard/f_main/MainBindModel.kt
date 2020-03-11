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

    val loginTextChanged = Action<String>()
    val passwordTextChanged = Action<String>()
    val loginButtonClicked = Action<Unit>()

    val loginError = State<String>()
    val passwordError = State<String>()
    val authorized = State<Boolean>()
}