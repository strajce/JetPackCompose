package com.example.jetpackcompose.network.responses

import com.example.jetpackcompose.Repository.RecipeRepository
import com.example.jetpackcompose.domain.model.Recipe
import com.example.jetpackcompose.network.RecipeService
import com.example.jetpackcompose.network.model.RecipeDtoMapper

class RecipeRepositoryImplementation(
    private val recipeService: RecipeService,
    private val recipeDtoMapper: RecipeDtoMapper
) : RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        return recipeDtoMapper.toDomainList(
            recipeService.search(
                token = token,
                page = page,
                query = query
            ).recipe
        )
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return recipeDtoMapper.mapToDomainModel(recipeService.get(token = token, id = id))
    }
}