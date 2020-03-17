package ru.surfstudio.standard.f_authorization

import com.example.i_main.AuthInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Презентер главного экрана
 */
@PerScreen
class AuthPresenter @Inject constructor(
        private val bindModel: AuthBindModel,
        private val authInteractor: AuthInteractor,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

        bindModel.loginChangedAction bindTo {
            validateLogin(it)
        }

        bindModel.passwordChangedAction bindTo {
            validatePassword(it)
        }

        bindModel.authorizeAction bindTo {
            if(bindModel.loginValidated && bindModel.passwordValidated){
                authInteractor.authorize(it.first, it.second)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .timeout(5, TimeUnit.SECONDS)
                        .subscribe({
                            bindModel.authorizedState.accept(true)
                        },{
                            bindModel.authorizedState.accept(false)
                        })
            } else {
                bindModel.authorizedState.accept(false)
            }
        }
    }

    fun validateLogin(login: String): Boolean {
        return when {
            login.trim().isEmpty() -> {
                bindModel.loginErrorState.accept("Поле не может быть пустым")
                bindModel.loginValidated = false
                false
            }
            else -> {
                bindModel.loginErrorState.accept("")
                bindModel.loginValidated = true
                true
            }
        }
    }

    fun validatePassword(password: String): Boolean {
        return when {
            password.trim().isEmpty() -> {
                bindModel.passwordErrorState.accept("Поле не может быть пустым")
                bindModel.passwordValidated = false
                false
            }
            password.trim().length != 6 -> {
                bindModel.passwordErrorState.accept("Пароль должен содержать 6 символов")
                bindModel.passwordValidated = false
                false
            }
            else -> {
                bindModel.passwordErrorState.accept("")
                bindModel.passwordValidated = true
                true
            }
        }
    }
}