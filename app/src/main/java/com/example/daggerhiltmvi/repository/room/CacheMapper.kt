package com.example.daggerhiltmvi.repository.room

import com.example.daggerhiltmvi.model.Blog
import com.example.daggerhiltmvi.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<BlogCacheEntity, Blog> {
    override fun entitytoMap(entity: BlogCacheEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            category = entity.category,
            image = entity.image
        )
    }

    override fun maptoEntity(domainModel: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            category = domainModel.category,
            image = domainModel.image
        )
    }

    fun mapFromEntityList(entities: List<BlogCacheEntity>): List<Blog>{
        return entities.map{entitytoMap(it)}
    }
}