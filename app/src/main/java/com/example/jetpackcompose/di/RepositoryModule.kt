package com.example.jetpackcompose.di

import com.example.jetpackcompose.Repository.RecipeRepository
import com.example.jetpackcompose.network.RecipeService
import com.example.jetpackcompose.network.model.RecipeDtoMapper
import com.example.jetpackcompose.network.responses.RecipeRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository {
        return RecipeRepositoryImplementation(
            recipeService = recipeService,
            recipeDtoMapper = recipeDtoMapper
        )
    }
}