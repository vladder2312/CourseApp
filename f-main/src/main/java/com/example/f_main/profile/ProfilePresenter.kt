package com.example.f_main.profile

import com.example.f_main.profile.exitDialog.ExitDialogRoute
import com.example.f_meme.MemeActivityRoute
import com.example.i_main.AuthInteractor
import com.example.i_main.UserRepository
import com.example.i_memes.MemesInteractor
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.core.ui.navigation.activity.navigator.ActivityNavigator
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.android.mvp.dialog.navigation.navigator.DialogNavigator
import ru.surfstudio.standard.domain.feed.Meme
import ru.surfstudio.standard.ui.navigation.AuthActivityRoute
import ru.surfstudio.standard.ui.navigation.ShareRoute
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Презентер экрана профиля
 * */
@PerScreen
class ProfilePresenter @Inject constructor(
        private val bindModel: ProfileBindModel,
        private val dialogNavigator: DialogNavigator,
        private val activityNavigator: ActivityNavigator,
        private val memesInteractor: MemesInteractor,
        private val authInteractor: AuthInteractor,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

        bindModel.openExitDialog bindTo ::openExitDialog
        bindModel.refreshAction bindTo ::loadMemes
        bindModel.openMemeAction bindTo { openMeme(it) }
        bindModel.shareMemeAction bindTo ::shareMeme

        bindModel.userDataState.accept(Pair(authInteractor.userRepository.getName(), authInteractor.userRepository.getDescription()))
    }

    private fun openExitDialog(){
        dialogNavigator.show(ExitDialogRoute())
    }

    private fun loadMemes(){
        bindModel.memesState.accept(memesInteractor.getMemesFromStorage())
    }

    private fun openMeme(meme: Meme) = activityNavigator.start(MemeActivityRoute(meme))

    private fun shareMeme(meme: Meme) {
        activityNavigator.start(ShareRoute("${meme.title}\n${meme.description}"))
    }

    fun exit(){
        authInteractor.logout()
        activityNavigator.start(AuthActivityRoute())
        activityNavigator.finishCurrent()
    }
}