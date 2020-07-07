package dev.pinaki.receepee.data.source.remote

import dev.pinaki.receepee.data.model.Recipe
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface RecipeApiService {

    @GET("recipes.json")
    suspend fun getAllRecipes(): List<Recipe>
}