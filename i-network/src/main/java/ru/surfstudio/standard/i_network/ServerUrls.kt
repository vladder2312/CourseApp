package ru.surfstudio.standard.i_network

import ru.surfstudio.android.template.i_network.BuildConfig

/**
 * URL всех серверных запросов
 */

//todo определить проектные url и path

const val BASE_API_URL = BuildConfig.BASE_URL

const val TEST_API_URL = "https://demo3161256.mockable.io"

const val USER_LOGOUT_PATH = "/logout"

const val GET_TOKEN_PATH = "/connect/token"

const val LOGIN_BY_PHONE_PATH = "/login/phone"