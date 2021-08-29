package com.example.daggerhiltmvi.repository

import com.example.daggerhiltmvi.model.Blog
import com.example.daggerhiltmvi.repository.retrofit.BlogRetrofit
import com.example.daggerhiltmvi.repository.room.BlogDao
import com.example.daggerhiltmvi.repository.room.CacheMapper
import com.example.daggerhiltmvi.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.lang.Exception


class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){
    suspend fun getBlot(): kotlinx.coroutines.flow.Flow<DataState<List<Blog>>> = flow{
        emit(DataState.Loading)
            delay(1000)
        try {
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs){
                blogDao.insert(cacheMapper.maptoEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}
