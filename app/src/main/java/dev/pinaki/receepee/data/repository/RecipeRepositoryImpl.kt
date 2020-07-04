package dev.pinaki.receepee.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.pinaki.receepee.data.model.Recipe
import dev.pinaki.receepee.data.source.getSampleData

class RecipeRepositoryImpl : RecipeRepository {
    override fun getAllRecipes(): LiveData<List<Recipe>> {
        return MutableLiveData(getSampleData())
    }

    override fun getRecipeById(id: Int): LiveData<Recipe?> {
        return MutableLiveData(getSampleData().first { it.id == id })
    }
}