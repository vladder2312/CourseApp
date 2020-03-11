package com.example.i_main.response

import com.google.gson.annotations.SerializedName

data class UserInfoObj(
        @SerializedName("id")val id : Int,
        @SerializedName("username") val username : String,
        @SerializedName("firstName")val firstName : String,
        @SerializedName("lastName") val lastName : String,
        @SerializedName("userDescription") val userDescription : String
)