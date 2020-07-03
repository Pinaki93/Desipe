package dev.pinaki.receepee.data.model

data class Recipe(
    val id: Int,
    val title: String,
    val subtitle: String,
    val image: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>
)

data class Ingredient(val name: String, val quantity: Int, val unit: String)

data class RecipeImage(val imageUrl: String, val attribution: String)