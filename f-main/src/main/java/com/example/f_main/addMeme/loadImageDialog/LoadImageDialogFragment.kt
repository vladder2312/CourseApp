package com.example.f_main.addMeme.loadImageDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.f_main.R
import com.example.f_main.addMeme.AddMemePresenter
import kotlinx.android.synthetic.main.dialog_load_image.*
import ru.surfstudio.android.mvp.dialog.simple.CoreSimpleDialogFragment
import javax.inject.Inject

class LoadImageDialogFragment : CoreSimpleDialogFragment() {

    @Inject
    lateinit var presenter: AddMemePresenter

    override fun getName() = "Load Image Dialog Fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getScreenComponent(LoadImageDialogComponent::class.java).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.dialog_load_image, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    fun initListeners(){
        text_camera.setOnClickListener {
            presenter.loadFromCamera()
            dismiss()
        }
        text_gallery.setOnClickListener {
            presenter.loadFromGallery()
            dismiss()
        }
    }
}