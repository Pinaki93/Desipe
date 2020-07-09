package dev.pinaki.desipe.data.source.local.db.converter

import androidx.room.TypeConverter
import dev.pinaki.desipe.data.model.Ingredient
import dev.pinaki.desipe.helper.moshi.DesipeMoshiHelper

class DataConverter {

    @TypeConverter
    fun convertStringListToJson(list: List<String>): String {
        return DesipeMoshiHelper.stringListAdapter.toJson(list)
    }

    @TypeConverter
    fun convertJsonToStringList(json: String): List<String> {
        return DesipeMoshiHelper.stringListAdapter.fromJson(json)!!
    }

    @TypeConverter
    fun convertIngredientListToJson(list: List<Ingredient>): String {
        return DesipeMoshiHelper.ingredientsListAdapter.toJson(list)
    }

    @TypeConverter
    fun convertJsonToIngredientList(json: String): List<Ingredient> {
        return DesipeMoshiHelper.ingredientsListAdapter.fromJson(json)!!
    }
}