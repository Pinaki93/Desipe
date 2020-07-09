package dev.pinaki.desipe.data.source.remote

import dev.pinaki.desipe.data.model.Recipe
import retrofit2.http.GET

interface RecipeApiService {

    @GET("recipes.json")
    suspend fun getAllRecipes(): List<Recipe>
}