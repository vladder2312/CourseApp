package com.example.i_memes

import com.example.i_memes.response.MemesResponse
import io.reactivex.Single
import retrofit2.http.GET
import ru.surfstudio.standard.i_network.MEMES_GET_PATH

interface MemesApi {

    @GET(MEMES_GET_PATH)
    fun getMemes() : Single<List<MemesResponse>>
}