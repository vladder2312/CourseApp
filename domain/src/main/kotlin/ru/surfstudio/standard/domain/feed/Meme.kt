package ru.surfstudio.standard.domain.feed

data class Meme(
        val id: String,
        val createdDate: Int,
        val description: String,
        val isFavorite: Boolean,
        val photoUtl: String,
        val title: String
)