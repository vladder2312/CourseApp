package ru.surfstudio.standard.domain.feed

import java.io.Serializable

data class Meme(
        val id: String,
        val createdDate: Long,
        val description: String,
        val isFavorite: Boolean,
        val photoUtl: String,
        val title: String
) : Serializable