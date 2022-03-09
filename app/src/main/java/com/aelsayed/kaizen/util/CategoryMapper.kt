package com.aelsayed.kaizen.util

import com.aelsayed.kaizen.data.local.entity.CategoryEntity
import com.aelsayed.kaizen.data.remote.dto.SportInfoResponse
import com.aelsayed.kaizen.domain.model.Category

object CategoryMapper {


    fun entityToModel(categoryEntity: CategoryEntity): Category{
        return Category(
            id = categoryEntity.id,
            name = categoryEntity.name
        )
    }

    fun dtoToEntity(sportInfoResponse: SportInfoResponse): CategoryEntity{
        return CategoryEntity(
            id = sportInfoResponse.sportInfoId,
            name = sportInfoResponse.category
        )
    }

}