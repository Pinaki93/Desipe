package dev.pinaki.receepee.data.repository

import androidx.lifecycle.LiveData
import dev.pinaki.receepee.data.model.Recipe

interface RecipeRepository {
    fun getAllRecipes(): LiveData<List<Recipe>>

    fun getRecipeById(id: Int): LiveData<Recipe?>
}