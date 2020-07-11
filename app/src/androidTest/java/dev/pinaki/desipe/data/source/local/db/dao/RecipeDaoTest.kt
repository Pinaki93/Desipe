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

package dev.pinaki.desipe.data.source.local.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dev.pinaki.desipe.data.source.local.db.DesipeDatabase
import dev.pinaki.desipe.getSampleData
import dev.pinaki.desipe.testutil.getValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@Suppress("BlockingMethodInNonBlockingContext")
@RunWith(AndroidJUnit4::class)
class RecipeDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var database: DesipeDatabase
    lateinit var dao: RecipeDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, DesipeDatabase::class.java).build()
        dao = database.recipeDao()
    }

    @Test
    fun testInsert() = runBlockingTest {
        dao.saveAllRecipes(getSampleData())
        val allRecipes = getValue(dao.getAllRecipes())
        assertThat(allRecipes.size, `is`(8))
    }

    @Test
    fun test_getRecipeById() = runBlockingTest {
        dao.saveAllRecipes(getSampleData())
        val recipe = getValue(dao.getRecipeForId(1))
        assertThat(recipe.title, `is`("Desi Lamb Tikka Burger"))
    }

    @Test
    fun testDeleteAll() = runBlockingTest {
        dao.saveAllRecipes(getSampleData())
        dao.deleteAll()
        val allRecipes = getValue(dao.getAllRecipes())
        assertThat(allRecipes.size, `is`(0))
    }
}