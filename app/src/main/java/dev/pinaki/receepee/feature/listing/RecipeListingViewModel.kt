package dev.pinaki.receepee.feature.listing

import androidx.lifecycle.ViewModel
import dev.pinaki.receepee.data.model.Recipe
import dev.pinaki.receepee.data.repository.RecipeRepository
import timber.log.Timber

class RecipeListingViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {
    val allRecipes = recipeRepository.getAllRecipes()

    fun onRecipeItemClick(recipe: Recipe) {
        Timber.tag("RecipeListingViewModel").i("clicked: $recipe")
    }
}