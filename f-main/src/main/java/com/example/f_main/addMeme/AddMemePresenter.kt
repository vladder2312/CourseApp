package com.example.f_main.addMeme

import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import javax.inject.Inject

class AddMemePresenter @Inject constructor(
        private val bindModel: AddMemeBindModel,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

}