package com.example.f_main.addMeme

import android.util.Log
import com.example.f_main.addMeme.loadImageDialog.LoadImageDialogRoute
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.android.mvp.dialog.navigation.navigator.DialogNavigator
import ru.surfstudio.android.picturechooser.PicturePermissionChecker
import ru.surfstudio.android.picturechooser.PictureProvider
import timber.log.Timber
import javax.inject.Inject

/**
 * Презентер экрана добавления мема
 **/
@PerScreen
class AddMemePresenter @Inject constructor(
        private val bindModel: AddMemeBindModel,
        private val dialogNavigator: DialogNavigator,
        private val pictureProvider: PictureProvider,
        private val picturePermissionChecker: PicturePermissionChecker,
        basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)

        bindModel.loadImageAction bindTo ::loadImage
        bindModel.createMemeAction bindTo ::saveMeme
    }

    fun loadFromCamera() {
        subscribeIoHandleError(picturePermissionChecker.checkCameraStoragePermission()) {
            if (it) {
                subscribeIoHandleError(
                        pictureProvider.openCameraAndTakePhoto(),
                        { camResult ->
                            bindModel.imageState.accept(camResult.photoUrl)
                        },
                        { throwable ->
                            Timber.d(throwable.localizedMessage)
                        })
            }
        }
    }

    fun loadFromGallery() {
        subscribeIoHandleError(
                pictureProvider.openGalleryAndGetPhoto()
        ) {
            bindModel.imageState.accept(it)
        }
    }

    private fun loadImage() {
        dialogNavigator.show(LoadImageDialogRoute())
    }

    private fun saveMeme() {

    }
}