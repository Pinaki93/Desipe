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

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import dev.pinaki.desipe.common.connectivity.ConnectivityDetector
import dev.pinaki.desipe.common.coroutine.DispatcherProvider
import dev.pinaki.desipe.data.repository.RecipeRepository
import dev.pinaki.desipe.fake.FakeRecipeRepository
import dev.pinaki.desipe.getSampleData
import dev.pinaki.desipe.testutil.MainCoroutineRule
import dev.pinaki.desipe.testutil.getValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import java.lang.Exception

/**
 * Unit Tests for [RecipeListingViewModel]
 * - given a non-empty list of recipe items, items are displayed successfully
 * - given a non-empty list of recipe items when the internet is disconnected,
 * items are displayed successfully
 * - when the recipe list is empty, and internet connection is absent, offline message is shown
 *
 * - when the recipe list is empty, and internet connection is present
 * and data couldn't be loaded from the webservice, error view is shown
 *
 * - when the recipe list is empty, and internet connection is present and empty recipe list
 * is returned from the webservice, error view is shown
 *
 * - when the recipe list is empty, and internet connection is present and data is loaded
 * from the webservice, error view is not shown

 */
@ExperimentalCoroutinesApi
class RecipeListingViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: RecipeListingViewModel

    @MockK
    lateinit var recipeRepository: RecipeRepository

    @MockK
    lateinit var connectivityDetector: ConnectivityDetector

    @MockK
    lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `when the recipe list is non empty, items are displayed successfully`() {
        // given- repository returns non empty data
        coEvery { recipeRepository.getAllRecipes() } returns MutableLiveData(getSampleData())

        // when - viewmodel is instantiated
        viewModel =
            RecipeListingViewModel(recipeRepository, connectivityDetector, dispatcherProvider)

        // then - recipe list is displayed successfully
        val recipeListToDisplay = getValue(viewModel.allRecipes)
        assert(recipeListToDisplay.isNotEmpty())
    }

    @Test
    fun `when the recipe list is non empty and internet is disconnected, items are displayed successfully`() {
        // given- repository returns non empty data
        coEvery { recipeRepository.getAllRecipes() } returns MutableLiveData(getSampleData())
        coEvery { connectivityDetector.isConnected() } returns false

        // when - viewmodel is instantiated
        viewModel =
            RecipeListingViewModel(recipeRepository, connectivityDetector, dispatcherProvider)

        // then - recipe list is displayed successfully
        val recipeListToDisplay = getValue(viewModel.allRecipes)
        assert(recipeListToDisplay.isNotEmpty())
    }

    @Test
    fun `when the recipe list is empty, and internet connection is absent, offline message is shown`() {
        // given- repository returns empty data and internet is absent
        coEvery { recipeRepository.getAllRecipes() } returns MutableLiveData(ArrayList())
        coEvery { connectivityDetector.isConnected() } returns false

        // when - viewmodel is instantiated
        viewModel =
            RecipeListingViewModel(recipeRepository, connectivityDetector, dispatcherProvider)

        // then - offline message is shown
        val recipeListToDisplay = getValue(viewModel.allRecipes)
        val showOffline = getValue(viewModel.showOffline)
        assert(recipeListToDisplay.isEmpty())
        assert(showOffline)
    }

    @Test
    fun `when the recipe list is empty & data couldn't be loaded from the webservice, error view is shown`() =
        runBlocking {
            // given- repository returns empty data and webservice throws exception
            coEvery { recipeRepository.getAllRecipes() } returns MutableLiveData(ArrayList())
            coEvery { connectivityDetector.isConnected() } returns true
            coEvery { recipeRepository.syncRecipes() } throws Exception()
            coEvery { dispatcherProvider.io() } returns mainCoroutineRule.getMainDispatcher()

            // when - viewmodel is instantiated
            viewModel =
                RecipeListingViewModel(recipeRepository, connectivityDetector, dispatcherProvider)

            // then - error message is shown
            val recipeListToDisplay = getValue(viewModel.allRecipes)
            val showLoading = getValue(viewModel.showLoading)
            val showError = getValue(viewModel.showError)
            assert(recipeListToDisplay.isEmpty())
            assert(!showLoading)
            assert(showError)
        }

    @Test
    fun `when the recipe list is empty and empty recipe list is returned from the webservice, error view is shown`() =
        runBlocking {
            // given- repository returns empty data and webservice returns empty list
            coEvery { recipeRepository.getAllRecipes() } returns MutableLiveData(ArrayList())
            coEvery { connectivityDetector.isConnected() } returns true
            coEvery { recipeRepository.syncRecipes() } returns ArrayList()
            coEvery { dispatcherProvider.io() } returns mainCoroutineRule.getMainDispatcher()

            // when - viewmodel is instantiated
            viewModel =
                RecipeListingViewModel(recipeRepository, connectivityDetector, dispatcherProvider)

            // then - error message is shown
            val recipeListToDisplay = getValue(viewModel.allRecipes)
            val showLoading = getValue(viewModel.showLoading)
            val showError = getValue(viewModel.showError)
            assert(recipeListToDisplay.isEmpty())
            assert(!showLoading)
            assert(showError)
        }

    @Test
    fun `when the recipe list is empty, and and data is loaded from the webservice, error view is not shown`() =
        runBlocking {
            // given- repository returns empty data and internet is absent
            coEvery { connectivityDetector.isConnected() } returns true
            coEvery { recipeRepository.syncRecipes() } returns ArrayList()
            coEvery { dispatcherProvider.io() } returns mainCoroutineRule.getMainDispatcher()

            // when - viewmodel is instantiated
            viewModel =
                RecipeListingViewModel(
                    FakeRecipeRepository(ArrayList()),
                    connectivityDetector,
                    dispatcherProvider
                )

            coEvery { recipeRepository.getAllRecipes() } returns MutableLiveData(ArrayList())

            // then - error message is not shown
            val recipeListToDisplay = getValue(viewModel.allRecipes)
            val showLoading = getValue(viewModel.showLoading)
            val showError = getValue(viewModel.showError)
            assert(recipeListToDisplay.isNotEmpty())
            assert(!showLoading)
            assert(!showError)
        }
}