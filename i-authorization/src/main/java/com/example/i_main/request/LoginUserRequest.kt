package com.example.i_main.request

import com.google.gson.annotations.SerializedName

data class LoginUserRequest(
        @SerializedName("login") val login: String,
        @SerializedName("password") val password: String
)