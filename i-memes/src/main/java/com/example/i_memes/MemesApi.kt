package com.example.i_memes

import com.example.i_memes.response.MemesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MemesApi {

    @GET("/memes")
    fun getMemes() : Single<List<MemesResponse>>
}