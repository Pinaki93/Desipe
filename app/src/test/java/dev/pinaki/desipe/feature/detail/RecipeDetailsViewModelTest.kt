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

package dev.pinaki.desipe.feature.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.pinaki.desipe.fake.FakeRecipeRepository
import dev.pinaki.desipe.getSampleData
import dev.pinaki.desipe.testutil.MainCoroutineRule
import dev.pinaki.desipe.testutil.getValue
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before

import org.junit.Rule
import org.junit.Test

/**
 * Unit Tests for [RecipeDetailsViewModel]
 * - when viewmodel is asked to load data with id, proper data is loaded
 */
@ExperimentalCoroutinesApi
class RecipeDetailsViewModelTest {

    lateinit var viewModelRecipe: RecipeDetailsViewModel

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun testLoad() {
        viewModelRecipe = RecipeDetailsViewModel(FakeRecipeRepository(getSampleData()))
        viewModelRecipe.load(1)

        val recipeLoaded = getValue(viewModelRecipe.recipe)
        assert(recipeLoaded.id == 1)
    }
}