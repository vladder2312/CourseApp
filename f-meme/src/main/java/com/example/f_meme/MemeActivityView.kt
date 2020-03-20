package com.example.f_meme

import android.os.Bundle
import android.os.PersistableBundle
import com.example.f_meme.di.MemeScreenConfigurator
import kotlinx.android.synthetic.main.activity_meme.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxActivityView
import ru.surfstudio.android.imageloader.ImageLoader
import javax.inject.Inject

class MemeActivityView : BaseRxActivityView() {

    @Inject
    lateinit var presenter: MemePresenter
    @Inject
    lateinit var bindModel: MemeBindModel

    override fun createConfigurator() = MemeScreenConfigurator(intent)
    override fun getContentView() = R.layout.activity_meme
    override fun getScreenName() = "MemeActivityView"

    override fun onCreate(
            savedInstanceState: Bundle?,
            persistentState: PersistableBundle?,
            viewRecreated: Boolean
    ) {
        super.onCreate(savedInstanceState, persistentState, viewRecreated)

        loadMeme()
    }

    private fun loadMeme(){
        titleFullMeme.text = intent.getStringExtra("title")
        descriptionFullMeme.text = intent.getStringExtra("description")
        ImageLoader.with(applicationContext)
                .url(intent.getStringExtra("imageUtl"))
                .into(imageFullMeme)
        dateFullMeme.text = intent.getStringExtra("createdDate")
    }
}