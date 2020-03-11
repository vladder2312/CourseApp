package com.example.i_main

import com.example.i_main.request.LoginUserRequest
import com.example.i_main.response.AuthInfoResponse
import io.reactivex.Single
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.standard.domain.login.AuthInfo
import ru.surfstudio.standard.domain.login.LogoutErrorInfo
import ru.surfstudio.standard.i_network.network.transform
import ru.surfstudio.standard.i_network.service.BaseNetworkService
import javax.inject.Inject

@PerApplication
class AuthRepository @Inject constructor(
        private val authApi: AuthApi
) : BaseNetworkService() {

    fun authorize(login: String, password: String): Single<AuthInfo> =
            authApi.authorize(LoginUserRequest(login,password)).transform()

    fun logout(): Single<LogoutErrorInfo> =
            authApi.logout().transform()
}