package com.example.i_memes

import io.reactivex.Single
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.standard.domain.feed.Meme
import javax.inject.Inject

@PerApplication
class MemesInteractor @Inject constructor(
        val memesRepository: MemesRepository
) {

    fun getMemes() : Single<List<Meme>> {
        return memesRepository.getMemes()
    }

    fun saveMemeToStorage(key: String, meme: Meme) = memesRepository.saveMemeToStorage(key, meme)

    fun getMemesFromStorage() = memesRepository.getMemesFromStorage()

    fun getMemeFromStorage(key: String) = memesRepository.getMemeFromStorage(key)

    fun clearMemesStorage() = memesRepository.clearMemesStorage()
}