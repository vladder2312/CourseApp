package ru.surfstudio.standard.f_main

import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Презентер главного экрана
 */
@PerScreen
class MainPresenter @Inject constructor(
        bindModel: MainBindModel,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    private val sm = MainBindModel()

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

    }
}