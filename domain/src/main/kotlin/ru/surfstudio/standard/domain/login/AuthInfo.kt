package ru.surfstudio.standard.domain.login

data class AuthInfo(
    val accessToken : String,
    val userInfo : UserInfo
)