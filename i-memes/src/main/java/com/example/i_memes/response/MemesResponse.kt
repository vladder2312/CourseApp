package com.example.i_memes.response

import com.google.gson.annotations.SerializedName
import ru.surfstudio.standard.domain.feed.Meme
import ru.surfstudio.standard.i_network.network.Transformable

data class MemesResponse(
        @SerializedName("id") val id: String,
        @SerializedName("createdDate") val createdDate: Long,
        @SerializedName("description") val description: String,
        @SerializedName("isFavorite") val isFavorite: Boolean,
        @SerializedName("photoUtl") val photoUtl: String,
        @SerializedName("title") val title: String
) : Transformable<Meme> {

    override fun transform(): Meme {
        return Meme(id, createdDate, description, isFavorite, photoUtl, title)
    }
}