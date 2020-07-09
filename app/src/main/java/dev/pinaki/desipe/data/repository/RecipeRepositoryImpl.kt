package dev.pinaki.desipe.data.repository

import androidx.lifecycle.LiveData
import dev.pinaki.desipe.data.model.Recipe
import dev.pinaki.desipe.data.source.local.db.dao.RecipeDao
import dev.pinaki.desipe.data.source.remote.RecipeApiService

class RecipeRepositoryImpl(
    private val recipeApiService: RecipeApiService,
    private val recipeDao: RecipeDao
) : RecipeRepository {

    override fun getAllRecipes(): LiveData<List<Recipe>> {
        return recipeDao.getAllRecipes()
    }

    override fun getRecipeById(id: Int): LiveData<Recipe> {
        return recipeDao.getRecipeForId(id)
    }

    override suspend fun syncRecipes(): List<Recipe> {
        return recipeApiService.getAllRecipes()
    }

    override suspend fun deleteAllRecipes() {
        recipeDao.deleteAll()
    }

    override suspend fun saveAllRecipes(recipes: List<Recipe>) {
        recipeDao.saveAllRecipes(recipes)
    }
}