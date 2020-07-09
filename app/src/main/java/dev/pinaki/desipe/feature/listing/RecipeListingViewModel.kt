/*
 * MIT License
 *
 * Copyright (c) 2020 Pinaki Acharya
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

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