package ru.surfstudio.standard.f_authorization

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import androidx.annotation.LayoutRes
import com.example.f_main.MainActivityView
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import kotlinx.android.synthetic.main.activity_authorization.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxActivityView
import ru.surfstudio.android.template.f_authorization.R
import ru.surfstudio.standard.f_authorization.di.AuthScreenConfigurator
import javax.inject.Inject

/**
 * Вью экрана авторизации
 */
class AuthActivityView : BaseRxActivityView() {

    @Inject
    lateinit var bindModel: AuthBindModel
    @Inject
    lateinit var presenter: AuthPresenter

    override fun createConfigurator() = AuthScreenConfigurator(intent)

    override fun getScreenName(): String = "AuthActivityView"

    @LayoutRes
    override fun getContentView(): Int = R.layout.activity_authorization

    override fun onCreate(
            savedInstanceState: Bundle?,
            persistentState: PersistableBundle?,
            viewRecreated: Boolean
    ) {

        initListeners()
        bind()
    }

    private fun bind() {
        loginText.textChanges() bindTo { bindModel.loginChangedAction.accept(loginText.text.toString()) }
        passwordText.textChanges() bindTo { bindModel.passwordChangedAction.accept(passwordText.text.toString()) }
        loginButton.clicks() bindTo {
            bindModel.authorizeAction.accept(loginText.text.toString() to passwordText.text.toString())
            showLoading()
        }

        bindModel.loginErrorState bindTo ::showLoginError
        bindModel.passwordErrorState bindTo ::showPasswordError
        bindModel.authorizedState bindTo {
            hideLoading()
            if (it) toNextScreen()
        }
    }

    private fun initListeners(){
        loginText.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    private fun showLoginError(string: String) {
        loginLabel.text = string
    }

    private fun showPasswordError(string: String) {
        passwordLabel.text = string
    }

    private fun showLoading() {
        loader.visibility = View.VISIBLE
        loginButton.text = ""
    }

    private fun hideLoading() {
        loader.visibility = View.INVISIBLE
        loginButton.text = "Войти"
    }

    private fun toNextScreen(){
        startActivity(Intent(this, MainActivityView::class.java))
    }
}
