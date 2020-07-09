package dev.pinaki.desipe.helper.moshi

import com.squareup.moshi.JsonAdapter
import dev.pinaki.desipe.common.util.MoshiHelper
import dev.pinaki.desipe.data.model.Ingredient
import dev.pinaki.desipe.data.model.Recipe

object DesipeMoshiHelper : MoshiHelper() {
    val ingredientsListAdapter: JsonAdapter<List<Ingredient>> = listAdapter()

    val stringListAdapter: JsonAdapter<List<String>> = listAdapter()

    val recipeListAdapter : JsonAdapter<List<Recipe>> = listAdapter()
}