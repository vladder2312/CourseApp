package com.example.f_main

import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

@PerScreen
class MainBindModel @Inject constructor() : BindModel {

    val feedClickedAction = Action<Unit>()
    val addMemeClickedAction = Action<Unit>()
    val profileClickedAction = Action<Unit>()
}