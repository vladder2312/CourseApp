package com.example.i_memes

import io.reactivex.Single
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.standard.domain.feed.Meme
import ru.surfstudio.standard.i_network.service.BaseNetworkService
import javax.inject.Inject

@PerApplication
class MemesRepository @Inject constructor(
        private val memesApi: MemesApi
) : BaseNetworkService() {

    fun getMemes() : Single<List<Meme>> =
            memesApi.getMemes().map {
                it.map {
                    it.transform()
                }
            }
}