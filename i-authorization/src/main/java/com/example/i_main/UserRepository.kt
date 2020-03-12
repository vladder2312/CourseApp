package com.example.i_main

import android.content.SharedPreferences
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.android.shared.pref.NO_BACKUP_SHARED_PREF
import ru.surfstudio.standard.domain.login.AuthInfo
import javax.inject.Inject
import javax.inject.Named

@PerApplication
class UserRepository @Inject constructor(
        @Named(NO_BACKUP_SHARED_PREF) private val noBackupSharedPreferences: SharedPreferences
) {

    fun saveUser(authInfo: AuthInfo) {
        val editor = noBackupSharedPreferences.edit()
        editor.putString("AccessToken", authInfo.accessToken)
        editor.putString("UserName", authInfo.userInfo.username)
        editor.putString("UserDescription", authInfo.userInfo.userDescription)
        editor.apply()
    }

    fun deleteUser() {
        val editor = noBackupSharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun getName() : String? {
        return noBackupSharedPreferences.getString("UserName", null)
    }

    fun getDescription() : String? {
        return noBackupSharedPreferences.getString("UserDescription", null)
    }
}