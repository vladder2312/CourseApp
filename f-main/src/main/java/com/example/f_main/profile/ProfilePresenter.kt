package com.example.f_main.profile

import com.example.i_main.UserRepository
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Презентер экрана профиля
 * */
@PerScreen
class ProfilePresenter @Inject constructor(
        private val bindModel: ProfileBindModel,
        private val userRepository: UserRepository,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

        bindModel.userDataState.accept(Pair(userRepository.getName(), userRepository.getDescription()))
    }
}