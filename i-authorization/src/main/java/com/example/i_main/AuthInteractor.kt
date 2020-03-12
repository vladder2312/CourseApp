package com.example.i_main

import io.reactivex.Single
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.standard.domain.login.LogoutErrorInfo
import ru.surfstudio.standard.domain.login.UserInfo
import javax.inject.Inject

@PerApplication
class AuthInteractor @Inject constructor(
        val authRepository: AuthRepository,
        val userRepository: UserRepository
) {

    fun authorize(login: String, password: String) : Single<UserInfo> {
        return authRepository.authorize(login, password).map {
            userRepository.saveUser(it)
            UserInfo(it.userInfo.username,it.userInfo.userDescription)
        }
    }

    fun logout(): Single<LogoutErrorInfo> {
        userRepository.deleteUser()
        return authRepository.logout()
    }
}