package com.example.i_memes

import io.reactivex.Single
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.standard.domain.feed.Meme
import ru.surfstudio.standard.i_network.service.BaseNetworkService
import javax.inject.Inject

@PerApplication
class MemesRepository @Inject constructor(
        private val memesApi: MemesApi,
        private val memesStorageWrapper: MemesStorageWrapper
) : BaseNetworkService() {

    fun getMemes() : Single<List<Meme>> =
            memesApi.getMemes().map {
                it.map {
                    it.transform()
                }
            }

    fun saveMemeToStorage(key: String, meme: Meme) = memesStorageWrapper.saveMeme(key, meme)

    fun getMemesFromStorage() = memesStorageWrapper.getMemes()

    fun getMemeFromStorage(key: String) = memesStorageWrapper.getMeme(key)

    fun clearMemesStorage() = memesStorageWrapper.clear()
}