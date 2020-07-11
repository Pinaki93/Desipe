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

package dev.pinaki.desipe.fake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.pinaki.desipe.data.model.Recipe
import dev.pinaki.desipe.data.repository.RecipeRepository
import dev.pinaki.desipe.getSampleData

class FakeRecipeRepository(initialList: List<Recipe>) : RecipeRepository {
    var recipesFromDB: MutableLiveData<List<Recipe>> = MutableLiveData(initialList)

    override fun getAllRecipes(): LiveData<List<Recipe>> {
        return recipesFromDB
    }

    override fun getRecipeById(id: Int): LiveData<Recipe> {
        val list = recipesFromDB.value?.filter { it.id == id } ?: throw Exception()
        return MutableLiveData(list[0])
    }

    override suspend fun syncRecipes(): List<Recipe> {
        return getSampleData()
    }

    override suspend fun deleteAllRecipes() {
        recipesFromDB.postValue(ArrayList())
    }

    override suspend fun saveAllRecipes(recipes: List<Recipe>) {
        recipesFromDB.postValue(recipes)
    }
}