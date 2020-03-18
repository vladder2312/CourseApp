package ru.surfstudio.standard.application.auth.di

import com.example.i_main.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.surfstudio.android.dagger.scope.PerApplication

@Module
class AuthModule {

    @Provides
    @PerApplication
    internal fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}