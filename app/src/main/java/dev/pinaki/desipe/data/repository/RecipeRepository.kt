package dev.pinaki.desipe.data.repository

import androidx.lifecycle.LiveData
import dev.pinaki.desipe.data.model.Recipe

interface RecipeRepository {
    fun getAllRecipes(): LiveData<List<Recipe>>

    fun getRecipeById(id: Int): LiveData<Recipe>

    suspend fun syncRecipes(): List<Recipe>

    suspend fun deleteAllRecipes()

    suspend fun saveAllRecipes(recipes: List<Recipe>)
}