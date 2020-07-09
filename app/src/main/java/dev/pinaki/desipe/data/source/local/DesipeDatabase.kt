package dev.pinaki.desipe.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.pinaki.desipe.BuildConfig
import dev.pinaki.desipe.data.model.Recipe
import dev.pinaki.desipe.data.source.local.db.converter.DataConverter
import dev.pinaki.desipe.data.source.local.db.dao.RecipeDao

@Database(
    entities = [Recipe::class],
    version = BuildConfig.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(value = [DataConverter::class])
abstract class DesipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}