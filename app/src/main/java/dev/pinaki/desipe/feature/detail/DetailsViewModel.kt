package dev.pinaki.desipe.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.pinaki.desipe.data.model.Recipe
import dev.pinaki.desipe.data.repository.RecipeRepository

class DetailsViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    lateinit var recipe: LiveData<Recipe>

    fun load(recipeId: Int) {
        recipe = recipeRepository.getRecipeById(recipeId)
    }
}
