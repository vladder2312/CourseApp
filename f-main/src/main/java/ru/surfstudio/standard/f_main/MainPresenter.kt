package ru.surfstudio.standard.f_main

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Презентер главного экрана
 */
@PerScreen
class MainPresenter @Inject constructor(
        private val bindModel: MainBindModel,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

        bindModel.loginTextChanged bindTo {
            validateLogin(it)
        }

        bindModel.passwordTextChanged bindTo {
            validatePassword(it)
        }

        bindModel.loginButtonClicked bindTo {
            if(bindModel.loginValidated && bindModel.passwordValidated){
                bindModel.authorized.accept(true)
            } else {
                bindModel.authorized.accept(false)
            }
        }
    }

    fun validateLogin(login: String): Boolean {
        return when {
            login.trim().isEmpty() -> {
                bindModel.loginError.accept("Поле не может быть пустым")
                bindModel.loginValidated = false
                false
            }
            else -> {
                bindModel.loginError.accept("")
                bindModel.loginValidated = true
                true
            }
        }
    }

    fun validatePassword(password: String): Boolean {
        return when {
            password.trim().isEmpty() -> {
                bindModel.passwordError.accept("Поле не может быть пустым")
                bindModel.passwordValidated = false
                false
            }
            password.trim().length != 6 -> {
                bindModel.passwordError.accept("Пароль должен содержать 6 символов")
                bindModel.passwordValidated = false
                false
            }
            else -> {
                bindModel.passwordError.accept("")
                bindModel.passwordValidated = true
                true
            }
        }
    }
}