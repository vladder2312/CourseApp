package com.example.i_main.response

import io.reactivex.Single
import ru.surfstudio.standard.domain.login.LogoutErrorInfo
import ru.surfstudio.standard.i_network.network.Transformable

data class LogoutErrorResponse(
        val code : String,
        val errorMessage: String
) : Transformable<LogoutErrorInfo>{

    override fun transform(): LogoutErrorInfo {
        return LogoutErrorInfo(code,errorMessage)
    }
}