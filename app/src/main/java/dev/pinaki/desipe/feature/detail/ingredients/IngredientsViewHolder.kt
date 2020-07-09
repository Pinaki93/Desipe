package dev.pinaki.desipe.feature.detail.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.pinaki.desipe.common.util.getIngredientQuantityText
import dev.pinaki.desipe.data.model.Ingredient
import dev.pinaki.desipe.databinding.IngredientViewBinding

class IngredientsViewHolder private constructor(private val binding: IngredientViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(ingredient: Ingredient) {
        with(binding) {
            textViewIngredientName.text = ingredient.name
            textViewIngredientQuantity.text = getIngredientQuantityText(
                textViewIngredientQuantity.context,
                ingredient.quantity,
                ingredient.unit
            )
        }
    }

    companion object {
        @JvmStatic
        fun from(parent: ViewGroup): IngredientsViewHolder {
            return IngredientsViewHolder(
                IngredientViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}