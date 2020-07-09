package dev.pinaki.receepee.feature.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import dev.pinaki.receepee.R
import dev.pinaki.receepee.common.util.getRecipeDetailsText
import dev.pinaki.receepee.common.util.px
import dev.pinaki.receepee.data.model.Recipe
import dev.pinaki.receepee.databinding.RecipeItemBinding

class RecipeListingViewHolder private constructor(
    private val recipeItemBinding: RecipeItemBinding
) : RecyclerView.ViewHolder(recipeItemBinding.root) {

    fun bind(recipe: Recipe, onClick: ((Recipe, ImageView) -> Unit)?) {
        with(recipeItemBinding) {
            textViewTitle.text = recipe.title
            textViewContent.text = recipe.subtitle
            textViewRecipeDetails.text = getRecipeDetailsText(
                textViewRecipeDetails.context,
                recipe.steps.size,
                recipe.ingredients.size
            )

            imageViewRecipe.transitionName = recipe.image

            imageViewRecipe.load(recipe.image) {
                crossfade(true)
                placeholder(R.drawable.listing_img_placeholder)
                transformations(RoundedCornersTransformation(4f.px))
            }

            root.setOnClickListener {
                onClick?.invoke(recipe, recipeItemBinding.imageViewRecipe)
            }
        }
    }

    companion object {
        @JvmStatic
        fun from(parent: ViewGroup): RecipeListingViewHolder {
            return RecipeListingViewHolder(
                RecipeItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}