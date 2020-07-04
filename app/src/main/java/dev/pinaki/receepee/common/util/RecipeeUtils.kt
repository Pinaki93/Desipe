package dev.pinaki.receepee.common.util

import android.content.Context
import dev.pinaki.receepee.R

fun getRecipeDetailsText(context: Context, stepCount: Int, ingredientCount: Int): String {
    return context.getString(R.string.recipe_details_text, stepCount, ingredientCount)
}

fun getIngredientQuantityText(context: Context, quantity: Int, unit: String): String {
    return context.getString(R.string.ingredient_quantity, quantity, unit)
}