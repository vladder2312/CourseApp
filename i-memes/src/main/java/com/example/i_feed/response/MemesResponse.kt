package com.example.i_feed.response

import ru.surfstudio.standard.domain.feed.Meme
import ru.surfstudio.standard.i_network.network.Transformable

data class MemesResponse(
        val createdDate: Int,
        val description: String,
        val id: String,
        val isFavorite: Boolean,
        val photoUrl: String,
        val title: String
) : Transformable<Meme> {

    override fun transform(): Meme {
        return Meme(id, createdDate, description, isFavorite, photoUrl, title)
    }
}