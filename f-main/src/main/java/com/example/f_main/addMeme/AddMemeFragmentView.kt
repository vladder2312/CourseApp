package com.example.f_main.addMeme

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.opengl.Visibility
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.f_main.R
import com.example.f_main.addMeme.di.AddMemeScreenConfigurator
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.view.enabled
import kotlinx.android.synthetic.main.fragment_addmeme.*
import kotlinx.android.synthetic.main.fragment_feed.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import ru.surfstudio.android.core.mvp.fragment.BaseRenderableFragmentView
import ru.surfstudio.android.core.mvp.presenter.CorePresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.imageloader.ImageLoader
import javax.inject.Inject

/**
 * Вью добавления мема
 * */
class AddMemeFragmentView : BaseRxFragmentView() {

    @Inject
    lateinit var bindModel: AddMemeBindModel
    @Inject
    lateinit var presenter: AddMemePresenter
    private val REQUEST_CAMERA = 22

    override fun getScreenName() = "AddMemeFragmentView"

    override fun createConfigurator() = AddMemeScreenConfigurator(Bundle.EMPTY)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_addmeme, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?, viewRecreated: Boolean) {
        super.onActivityCreated(savedInstanceState, viewRecreated)

        bind()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode==RESULT_OK && requestCode==REQUEST_CAMERA){
            val photo = data?.extras?.get("data") as Bitmap
            setImage(photo)
        }
    }

    private fun bind() {
        addMeme_button.clicks() bindTo { bindModel.loadImageAction.accept() }
        button_create_meme.clicks() bindTo { bindModel.createMemeAction.accept() }
        addmeme_delete.clicks() bindTo { deleteImage() }

        bindModel.imageState bindTo ::setImage
        bindModel.openCamera bindTo ::openCamera
    }

    private fun setImage(url: String) {
        context?.let {
            ImageLoader.with(it)
                    .url(url)
                    .into(addMeme_image)
            addmeme_delete.visibility = View.VISIBLE
            addMeme_button.isEnabled = false
            button_create_meme.isEnabled = false
            button_create_meme.setTextColor(resources.getColor(R.color.colorBlue))
        }
    }

    private fun setImage(photo : Bitmap) {
        addMeme_image.setImageBitmap(photo)
        addmeme_delete.visibility = View.VISIBLE
        addMeme_button.isEnabled = false
        button_create_meme.isEnabled = false
        button_create_meme.setTextColor(resources.getColor(R.color.colorBlue))
    }

    private fun deleteImage() {
        addMeme_image.setImageDrawable(null)
        addmeme_delete.visibility = View.INVISIBLE
        addMeme_button.isEnabled = true
        button_create_meme.isEnabled = false
        button_create_meme.setTextColor(resources.getColor(R.color.disabledButton))
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CAMERA)
    }
}