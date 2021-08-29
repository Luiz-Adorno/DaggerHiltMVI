package com.example.daggerhiltmvi.repository.di

import com.example.daggerhiltmvi.repository.MainRepository
import com.example.daggerhiltmvi.repository.NetworkMapper
import com.example.daggerhiltmvi.repository.retrofit.BlogRetrofit
import com.example.daggerhiltmvi.repository.room.BlogDao
import com.example.daggerhiltmvi.repository.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModel {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository{
        return MainRepository(blogDao, retrofit,cacheMapper,networkMapper)
    }
}