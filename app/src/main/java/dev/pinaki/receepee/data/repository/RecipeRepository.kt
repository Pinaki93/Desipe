package dev.pinaki.receepee.data.repository

import androidx.lifecycle.LiveData
import dev.pinaki.receepee.data.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getAllRecipes(): LiveData<List<Recipe>>

    fun getRecipeById(id: Int): LiveData<Recipe>

    suspend fun syncRecipes(): List<Recipe>

    suspend fun deleteAllRecipes()

    suspend fun saveAllRecipes(recipes: List<Recipe>)
}