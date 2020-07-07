package dev.pinaki.receepee.feature.listing

import androidx.lifecycle.*
import dev.pinaki.receepee.common.coroutines.DispatcherProvider
import dev.pinaki.receepee.common.ds.OneTimeEvent
import dev.pinaki.receepee.data.model.Recipe
import dev.pinaki.receepee.data.repository.RecipeRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class RecipeListingViewModel(
    private val recipeRepository: RecipeRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    val allRecipes = recipeRepository.getAllRecipes().map {
        if (it.isEmpty()) {
            syncRecipes()
        }

        return@map it
    }

    private val _showOffline = MutableLiveData<Boolean>()
    val showOffline: LiveData<Boolean> = _showOffline

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> = _showError

    private val _showDetails = MutableLiveData<OneTimeEvent<Int>>()
    val showDetails: LiveData<OneTimeEvent<Int>> = _showDetails

    fun syncRecipes() {
        _showLoading.value = true
        viewModelScope.launch(dispatcherProvider.io()) {
            try {
                val recipesFromServer = recipeRepository.syncRecipes()
                recipeRepository.deleteAllRecipes()
                recipeRepository.saveAllRecipes(recipesFromServer)
                _showError.postValue(false)
            } catch (e: Exception) {
                Timber.tag(TAG).e(e)
                _showError.postValue(true)
            }

            _showLoading.postValue(false)
        }
    }

    fun onRecipeItemClick(recipe: Recipe) {
        _showDetails.value = OneTimeEvent(recipe.id)
    }

    companion object {
        const val TAG = "RecipeListingViewModel"
    }
}