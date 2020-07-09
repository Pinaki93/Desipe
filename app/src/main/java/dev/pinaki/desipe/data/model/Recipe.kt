package dev.pinaki.desipe.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "recipe")
@JsonClass(generateAdapter = true)
data class Recipe(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "subtitle")
    val subtitle: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "ingredients")
    val ingredients: List<Ingredient>,

    @ColumnInfo(name = "steps")
    val steps: List<String>
)

@JsonClass(generateAdapter = true)
data class Ingredient(val name: String, val quantity: Int, val unit: String)