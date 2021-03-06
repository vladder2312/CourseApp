package com.example.f_main.addMeme

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.f_main.R
import com.example.f_main.addMeme.di.AddMemeScreenConfigurator
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import kotlinx.android.synthetic.main.fragment_addmeme.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import ru.surfstudio.android.imageloader.ImageLoader
import javax.inject.Inject

/**
 * Вью добавления мема
 * */
@Suppress("DEPRECATION")
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

        if (resultCode == RESULT_OK && requestCode == REQUEST_CAMERA) {
            val photo = data?.extras?.get("data") as Bitmap
            setImage(photo)
        }
    }

    private fun bind() {
        addMeme_title.textChanges() bindTo { bindModel.title = addMeme_title.text.toString() }
        addMeme_text.textChanges() bindTo { bindModel.description = addMeme_text.text.toString() }
        addMeme_button.clicks() bindTo { bindModel.loadImageAction.accept() }
        button_create_meme.clicks() bindTo { bindModel.createMemeAction.accept() }
        addmeme_delete.clicks() bindTo { deleteImage() }

        bindModel.imageState bindTo ::setImage
        bindModel.openCamera bindTo ::openCamera
    }

    private fun setImage(uri: String) {
        bindModel.photoUri = uri
        context?.let {
            ImageLoader.with(it)
                    .url(uri)
                    .into(addMeme_image)
            addmeme_delete.visibility = View.VISIBLE
            addMeme_button.isEnabled = false
            button_create_meme.isEnabled = true
            button_create_meme.setTextColor(resources.getColor(R.color.colorAccent))
        }
    }

    private fun setImage(photo: Bitmap) {
        addMeme_image.setImageBitmap(photo)
        addmeme_delete.visibility = View.VISIBLE
        addMeme_button.isEnabled = false
        button_create_meme.isEnabled = true
        button_create_meme.setTextColor(resources.getColor(R.color.colorAccent))
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