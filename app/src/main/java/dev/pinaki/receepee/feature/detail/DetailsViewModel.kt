package dev.pinaki.receepee.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.pinaki.receepee.data.model.Recipe
import dev.pinaki.receepee.data.repository.RecipeRepository

class DetailsViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    lateinit var recipe: LiveData<Recipe?>

    fun load(recipeId: Int) {
        recipe = recipeRepository.getRecipeById(recipeId)
    }
}
