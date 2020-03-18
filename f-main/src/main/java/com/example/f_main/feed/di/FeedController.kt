package com.example.f_main.feed.di

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.f_main.R
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import ru.surfstudio.android.imageloader.ImageLoader
import ru.surfstudio.standard.domain.feed.Meme

/**
 * Контроллер для списка мемов
 */
class FeedController(
        //private val onClickListener: (Meme) -> Unit
) : BindableItemController<Meme, FeedController.FeedHolder>() {

    inner class FeedHolder(parent: ViewGroup) : BindableViewHolder<Meme>(parent, R.layout.item_meme) {

        private val image : ImageView = itemView.findViewById(R.id.imageMeme)
        private val title : TextView = itemView.findViewById(R.id.titleMeme)

        override fun bind(meme: Meme) {
            ImageLoader.with(itemView.context)
                    .url(meme.photoUrl)
                    .into(image)
            title.text = meme.title
        }
    }

    override fun getItemId(meme: Meme) = meme.id

    override fun createViewHolder(parent: ViewGroup) = FeedHolder(parent)
}