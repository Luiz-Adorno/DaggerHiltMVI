package com.example.daggerhiltmvi.util

interface EntityMapper<Entity, DomainModel> {
    fun entitytoMap(entity: Entity): DomainModel

    fun maptoEntity(domainModel: DomainModel): Entity
}