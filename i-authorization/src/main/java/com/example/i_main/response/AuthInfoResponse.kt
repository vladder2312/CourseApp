package com.example.i_main.response

import com.google.gson.annotations.SerializedName
import ru.surfstudio.standard.domain.login.AuthInfo
import ru.surfstudio.standard.domain.login.UserInfo
import ru.surfstudio.standard.i_network.network.Transformable

data class AuthInfoResponse(
        @SerializedName("accessToken") val accessToken: String,
        @SerializedName("userInfo") val userInfo: UserInfoObj
) : Transformable<AuthInfo> {

    override fun transform(): AuthInfo {
        return AuthInfo(accessToken, UserInfo(userInfo.username, userInfo.userDescription))
    }
}
