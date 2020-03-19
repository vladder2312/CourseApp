package ru.surfstudio.standard.application.memes.di

import com.example.i_memes.MemesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.surfstudio.android.dagger.scope.PerApplication

@Module
class MemesModule {

    @Provides
    @PerApplication
    internal fun provideMemesApi(retrofit: Retrofit): MemesApi {
        return retrofit.create(MemesApi::class.java)
    }
}