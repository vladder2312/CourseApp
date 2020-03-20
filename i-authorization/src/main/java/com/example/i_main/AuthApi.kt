package com.example.i_main

import com.example.i_main.request.LoginUserRequest
import com.example.i_main.response.AuthInfoResponse
import com.example.i_main.response.LogoutErrorResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import ru.surfstudio.standard.i_network.USER_LOGIN_PATH
import ru.surfstudio.standard.i_network.USER_LOGOUT_PATH

interface AuthApi {

    @POST(USER_LOGIN_PATH)
    fun authorize(@Body authRequest: LoginUserRequest): Single<AuthInfoResponse>

    @POST(USER_LOGOUT_PATH)
    fun logout() : Single<LogoutErrorResponse>
}