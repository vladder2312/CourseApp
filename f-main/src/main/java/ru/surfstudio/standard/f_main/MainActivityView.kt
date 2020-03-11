package ru.surfstudio.standard.f_main

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.annotation.LayoutRes
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import kotlinx.android.synthetic.main.activity_main.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxActivityView
import ru.surfstudio.android.template.f_main.R
import ru.surfstudio.standard.f_main.di.MainScreenConfigurator
import javax.inject.Inject

/**
 * Вью главного экрана
 */
class MainActivityView : BaseRxActivityView() {

    @Inject
    lateinit var bindModel: MainBindModel

    @Inject
    lateinit var presenter: MainPresenter

    override fun createConfigurator() = MainScreenConfigurator(intent)

    override fun getScreenName(): String = "MainActivityView"

    @LayoutRes
    override fun getContentView(): Int = R.layout.activity_main

    override fun onCreate(
            savedInstanceState: Bundle?,
            persistentState: PersistableBundle?,
            viewRecreated: Boolean
    ) {
        bind()
    }

    fun bind() {

        loginText.textChanges() bindTo { bindModel.loginChangedAction.accept(loginText.text.toString()) }
        passwordText.textChanges() bindTo { bindModel.passwordChangedAction.accept(passwordText.text.toString()) }
        loginButton.clicks() bindTo {
            bindModel.authorizeAction.accept(loginText.text.toString() to passwordText.text.toString())
            showLoading()
        }

        bindModel.loginErrorState bindTo { showLoginError(it) }
        bindModel.passwordErrorState bindTo { showPasswordError(it) }
        bindModel.authorizedState bindTo {
            hideLoading()
            if (it) toNextScreen()
        }
    }

    fun showLoginError(string: String) {
        loginLabel.text = string
    }

    fun showPasswordError(string: String) {
        passwordLabel.text = string
    }

    fun showLoading() {
        loader.visibility = View.VISIBLE
        loginButton.text = ""
    }

    fun hideLoading() {
        loader.visibility = View.INVISIBLE
        loginButton.text = "Войти"
    }

    fun toNextScreen(){
        startActivity(Intent(this,MainActivityView::class.java))
    }
}
