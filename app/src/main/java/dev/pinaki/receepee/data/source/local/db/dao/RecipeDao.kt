package dev.pinaki.receepee.data.source.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.pinaki.receepee.data.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("select * from recipe")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Query("select * from recipe where id=:id")
    fun getRecipeForId(id: Int): LiveData<Recipe>

    @Insert
    suspend fun saveAllRecipes(recipes: List<Recipe>)

    @Query("delete from recipe")
    fun deleteAll()
}