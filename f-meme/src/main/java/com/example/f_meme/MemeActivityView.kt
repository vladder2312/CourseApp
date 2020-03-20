package com.example.f_meme

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import com.example.f_meme.di.MemeScreenConfigurator
import com.jakewharton.rxbinding2.view.clicks
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
        loadMeme()
        bind()
        if(intent.getBooleanExtra("isFavourite", false)) bindModel.likeClickedAction.accept()
    }

    private fun loadMeme(){
        titleFullMeme.text = intent.getStringExtra("title")
        descriptionFullMeme.text = intent.getStringExtra("description")
        ImageLoader.with(applicationContext)
                .url(intent.getStringExtra("imageUtl"))
                .into(imageFullMeme)
        dateFullMeme.text = intent.getStringExtra("createdDate")
    }

    private fun bind(){
        like_meme_btn.clicks() bindTo { bindModel.likeClickedAction.accept() }
        share_meme_btn.clicks() bindTo { bindModel.shareClickedAction.accept() }
        close_meme_btn.clicks() bindTo { this.finish() }

        bindModel.likeState bindTo { changeLikeImage() }
    }

    private fun changeLikeImage(){
        if(bindModel.liked){
            like_meme_btn.setImageResource(R.drawable.icon_like_filled)
        } else {
            like_meme_btn.setImageResource(R.drawable.icon_like)
        }
    }
}