package com.example.f_main.feed

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
        private val onClickImageListener: (Meme) -> Unit,
        private val onClickShareListener: (Meme) -> Unit
) : BindableItemController<Meme, FeedController.FeedHolder>() {

    inner class FeedHolder(parent: ViewGroup) : BindableViewHolder<Meme>(parent, R.layout.item_meme) {

        private val image: ImageView = itemView.findViewById(R.id.imageMeme)
        private val title: TextView = itemView.findViewById(R.id.titleMeme)
        private val like: ImageView = itemView.findViewById(R.id.likeMeme)
        private val share: ImageView = itemView.findViewById(R.id.shareMeme)
        private lateinit var meme: Meme

        init {
            image.setOnClickListener { onClickImageListener(meme) }
            like.setOnClickListener { changeLikeState() }
            share.setOnClickListener { onClickShareListener(meme) }
        }

        override fun bind(data: Meme) {
            meme = data
            ImageLoader.with(itemView.context)
                    .url(data.photoUtl)
                    .into(image)
            title.text = data.title
            if (meme.isFavorite) {
                like.setImageResource(R.drawable.icon_like_filled)
            }
        }

        private fun changeLikeState() {
            if (meme.isFavorite) {
                like.setImageResource(R.drawable.icon_like)
            } else {
                like.setImageResource(R.drawable.icon_like_filled)
            }
        }
    }

    override fun getItemId(meme: Meme) = meme.id

    override fun createViewHolder(parent: ViewGroup) = FeedHolder(parent)
}