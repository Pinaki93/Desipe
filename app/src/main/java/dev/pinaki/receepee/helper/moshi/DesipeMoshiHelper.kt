package dev.pinaki.receepee.helper.moshi

import com.squareup.moshi.JsonAdapter
import dev.pinaki.receepee.common.util.MoshiHelper
import dev.pinaki.receepee.data.model.Ingredient
import dev.pinaki.receepee.data.model.Recipe

object DesipeMoshiHelper : MoshiHelper() {
    val ingredientsListAdapter: JsonAdapter<List<Ingredient>> = listAdapter()

    val stringListAdapter: JsonAdapter<List<String>> = listAdapter()

    val recipeListAdapter : JsonAdapter<List<Recipe>> = listAdapter()
}