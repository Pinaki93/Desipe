package dev.pinaki.receepee.feature.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.pinaki.receepee.common.ds.OneTimeEvent
import dev.pinaki.receepee.data.model.Recipe
import dev.pinaki.receepee.data.repository.RecipeRepository

class RecipeListingViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {
    val allRecipes = recipeRepository.getAllRecipes()

    private val _showDetails = MutableLiveData<OneTimeEvent<Int>>()
    val showDetails: LiveData<OneTimeEvent<Int>> = _showDetails

    fun onRecipeItemClick(recipe: Recipe) {
        _showDetails.value = OneTimeEvent(recipe.id)
    }
}