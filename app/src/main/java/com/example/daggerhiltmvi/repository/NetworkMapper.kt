package com.example.daggerhiltmvi.repository

import com.example.daggerhiltmvi.model.Blog
import com.example.daggerhiltmvi.repository.retrofit.BlogNetworkEntity
import com.example.daggerhiltmvi.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<BlogNetworkEntity, Blog> {
    override fun entitytoMap(entity: BlogNetworkEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun maptoEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<BlogNetworkEntity>): List<Blog>{
        return entities.map { entitytoMap(it) }
    }
}