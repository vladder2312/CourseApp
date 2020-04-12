package ru.surfstudio.standard.application.memes.di

import android.content.Context
import com.example.i_memes.MemesApi
import com.example.i_memes.MemesStorageWrapper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.android.filestorage.CacheConstant
import ru.surfstudio.android.filestorage.converter.JsonConverter
import ru.surfstudio.android.filestorage.naming.NamingProcessor
import ru.surfstudio.android.filestorage.processor.FileProcessor
import ru.surfstudio.android.filestorage.storage.BaseJsonFileStorage
import ru.surfstudio.android.filestorage.utils.AppDirectoriesProvider
import ru.surfstudio.standard.domain.feed.Meme
import javax.inject.Named

@Module
class MemesModule {

    @Provides
    @PerApplication
    internal fun provideMemesApi(retrofit: Retrofit): MemesApi {
        return retrofit.create(MemesApi::class.java)
    }

    @Provides
    @PerApplication
    internal fun provideMemeObjectConverter(): JsonConverter<Meme> = JsonConverter(Meme::class.java)

    @Provides
    @PerApplication
    internal fun provideMemeCacheDirName(): String = "memes_storage"

    @Provides
    @PerApplication
    @Named(CacheConstant.INTERNAL_CACHE_DIR_DAGGER_NAME)
    fun provideInternalCacheDir(context: Context): String {
        return AppDirectoriesProvider.provideNoBackupStorageDir(context)
    }

    @Provides
    @PerApplication
    internal fun provideFileProcessor(
            @Named(CacheConstant.INTERNAL_CACHE_DIR_DAGGER_NAME) cacheDir: String,
            cacheDirName: String
    ): FileProcessor {
        return FileProcessor(cacheDir, cacheDirName, 50)
    }

    @Provides
    @PerApplication
    internal fun provideNamingProcessor(): NamingProcessor {
        return NamingProcessor { rawName -> rawName }
    }

    @Provides
    @PerApplication
    internal fun provideBaseFileStorage(
            fileProcessor: FileProcessor,
            namingProcessor: NamingProcessor
    ): BaseJsonFileStorage<Meme> {
        return BaseJsonFileStorage(fileProcessor, namingProcessor, Meme::class.java)
    }

    @Provides
    @PerApplication
    internal fun provideMemesStorageWrapper(
            baseJsonFileStorage: BaseJsonFileStorage<Meme>
    ): MemesStorageWrapper {
        return MemesStorageWrapper(baseJsonFileStorage)
    }
}