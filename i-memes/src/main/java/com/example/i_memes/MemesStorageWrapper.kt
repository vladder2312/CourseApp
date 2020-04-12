package com.example.i_memes

import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.android.filestorage.storage.BaseJsonFileStorage
import ru.surfstudio.standard.domain.feed.Meme
import javax.inject.Inject

@PerApplication
class MemesStorageWrapper @Inject constructor(
    private val memesFileStorage : BaseJsonFileStorage<Meme>
) {


    fun saveMeme(key: String, meme: Meme) = memesFileStorage.put(key, meme)

    fun getMemes() = memesFileStorage.all

    fun getMeme(key: String) = memesFileStorage.get(key)

    fun clear() = memesFileStorage.clear()
}