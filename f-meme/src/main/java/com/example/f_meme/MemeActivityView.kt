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
        dateFullMeme.text = getTimeAgo(intent.getLongExtra("createdDate",0))
        ImageLoader.with(applicationContext)
                .url(intent.getStringExtra("imageUtl"))
                .into(imageFullMeme)
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

    private fun getTimeAgo(time: Long): String? {
        val SECOND_MILLIS = 1000
        val MINUTE_MILLIS = 60 * SECOND_MILLIS
        val HOUR_MILLIS = 60 * MINUTE_MILLIS
        val DAY_MILLIS = 24 * HOUR_MILLIS

        var time = time
        if (time < 1000000000000L) {
            time *= 1000
        }

        val now = System.currentTimeMillis()
        if (time > now || time <= 0) {
            return null
        }

        val diff = now - time
        return when {
            diff < MINUTE_MILLIS -> " только что"
            diff < 2 * MINUTE_MILLIS -> " минуты назад"
            diff < 50 * MINUTE_MILLIS -> (diff / MINUTE_MILLIS).toString() + " минут назад"
            diff < 90 * MINUTE_MILLIS -> " час назад"
            diff < 24 * HOUR_MILLIS -> (diff / HOUR_MILLIS).toString() + " часов назад"
            diff < 48 * HOUR_MILLIS -> "вчера"
            else -> (diff / DAY_MILLIS).toString() + " дней назад"
        }
    }
}