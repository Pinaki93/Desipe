package dev.pinaki.desipe.feature.listing

import androidx.lifecycle.*
import dev.pinaki.desipe.common.connectivity.ConnectivityDetector
import dev.pinaki.desipe.common.coroutines.DispatcherProvider
import dev.pinaki.desipe.data.repository.RecipeRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class RecipeListingViewModel(
    private val recipeRepository: RecipeRepository,
    private val connectivityDetector: ConnectivityDetector,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    val allRecipes = recipeRepository.getAllRecipes().map {
        if (it.isEmpty()) {
            syncRecipes()
        }

        return@map it
    }

    private val _showOffline = MutableLiveData<Boolean>()
    val showOffline: LiveData<Boolean> = _showOffline.distinctUntilChanged()

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading.distinctUntilChanged()

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> = _showError.distinctUntilChanged()

    fun syncRecipes() {
        if (showLoading.value == true) return // already loading

        if (!connectivityDetector.isConnected()) {
            _showOffline.value = true
            return
        }

        _showOffline.value = false
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

    companion object {
        const val TAG = "RecipeListingViewModel"
    }
}