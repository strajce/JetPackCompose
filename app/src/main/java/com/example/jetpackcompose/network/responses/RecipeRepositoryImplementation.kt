package com.example.jetpackcompose.network.responses

import com.example.jetpackcompose.Repository.RecipeRepository
import com.example.jetpackcompose.domain.model.Recipe
import com.example.jetpackcompose.network.RecipeService
import com.example.jetpackcompose.network.model.RecipeDtoMapper

class RecipeRepositoryImplementation(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper
) : RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        return mapper.toDomainList(
            recipeService.search(
                token = token,
                page = page,
                query = query
            ).recipe
        )
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return mapper.mapToDomainModel(recipeService.get(token = token, id = id))
    }
}