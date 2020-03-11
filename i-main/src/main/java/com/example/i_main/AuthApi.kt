package com.example.i_main

import com.example.i_main.request.LoginUserRequest
import com.example.i_main.response.AuthInfoResponse
import com.example.i_main.response.LogoutErrorResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/auth/login")
    fun authorize(@Body authRequest: LoginUserRequest): Single<AuthInfoResponse>

    @POST("/auth/logout")
    fun logout() : Single<LogoutErrorResponse>
}