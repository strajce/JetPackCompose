package com.example.jetpackcompose.network.model

import com.example.jetpackcompose.domain.util.EntityMapper
import com.example.jetpackcompose.domain.model.Recipe

class RecipeNetworkMapper: EntityMapper<RecipeDTO, Recipe > {
    override fun mapFromEntity(entity: RecipeDTO): Recipe {
        return Recipe(
            id = entity.pk,
            title = entity.title,
            featuredImage = entity.featuredImage,
            rating = entity.rating,
            publisher = entity.publisher,
            sourceUrl = entity.sourceUrl,
            description = entity.description,
            cookingInstructions = entity.cookingInstructions,
            ingredients = entity.ingredients,
            dateAdded = entity.dateAdded,
            dateUpdated = entity.dateUpdated,
        )
    }

    override fun mapToEntity(domainModel: Recipe): RecipeDTO {
        return RecipeDTO(
            pk = domainModel.id,
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating,
            publisher = domainModel.publisher,
            sourceUrl = domainModel.sourceUrl,
            description = domainModel.description,
            cookingInstructions = domainModel.cookingInstructions,
            ingredients = domainModel.ingredients,
            dateAdded = domainModel.dateAdded,
            dateUpdated = domainModel.dateUpdated,
        )
    }

    fun fromEntityList(initial: List<RecipeDTO>): List<Recipe> {
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<Recipe>): List<RecipeDTO> {
        return initial.map { mapToEntity(it)}
    }
}