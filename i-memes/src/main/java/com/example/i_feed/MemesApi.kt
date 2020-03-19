package com.example.i_feed

import com.example.i_feed.response.MemesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MemesApi {

    @GET("/memes")
    fun getMemes() : Single<List<MemesResponse>>
}