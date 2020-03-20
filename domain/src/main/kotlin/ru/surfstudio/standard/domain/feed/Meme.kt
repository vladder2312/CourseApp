package ru.surfstudio.standard.domain.feed

data class Meme(
        val id: String,
        val createdDate: Long,
        val description: String,
        val isFavorite: Boolean,
        val photoUtl: String,
        val title: String
)