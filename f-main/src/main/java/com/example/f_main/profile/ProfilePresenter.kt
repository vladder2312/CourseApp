package com.example.f_main.profile

import com.example.f_main.profile.exitDialog.ExitDialogRoute
import com.example.i_main.AuthInteractor
import com.example.i_main.UserRepository
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.core.ui.navigation.activity.navigator.ActivityNavigator
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.android.mvp.dialog.navigation.navigator.DialogNavigator
import ru.surfstudio.standard.ui.navigation.AuthActivityRoute
import javax.inject.Inject

/**
 * Презентер экрана профиля
 * */
@PerScreen
class ProfilePresenter @Inject constructor(
        private val bindModel: ProfileBindModel,
        private val dialogNavigator: DialogNavigator,
        private val activityNavigator: ActivityNavigator,
        private val authInteractor: AuthInteractor,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

        bindModel.openExitDialog bindTo ::openExitDialog

        bindModel.userDataState.accept(Pair(authInteractor.userRepository.getName(), authInteractor.userRepository.getDescription()))
    }

    private fun openExitDialog(){
        dialogNavigator.show(ExitDialogRoute())
    }

    fun exit(){
        authInteractor.logout()
        activityNavigator.start(AuthActivityRoute())
        activityNavigator.finishCurrent()
    }
}