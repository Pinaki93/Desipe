/*
 * MIT License
 *
 * Copyright (c) 2020 Pinaki Acharya
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dev.pinaki.desipe.feature.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import dev.pinaki.desipe.R
import dev.pinaki.desipe.common.util.getRecipeDetailsText
import dev.pinaki.desipe.common.util.px
import dev.pinaki.desipe.data.model.Recipe
import dev.pinaki.desipe.databinding.RecipeItemBinding
import dev.pinaki.desipe.helper.util.loadRelativeUrl

class RecipeListingViewHolder private constructor(
    private val binding: RecipeItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(recipe: Recipe, onClick: ((Recipe, ImageView) -> Unit)?) {
        with(binding) {
            textViewTitle.text = recipe.title
            textViewContent.text = recipe.subtitle
            textViewRecipeDetails.text =
                getRecipeDetailsText(
                    textViewRecipeDetails.context,
                    recipe.steps.size,
                    recipe.ingredients.size
                )

            val imageUrl = recipe.image
            imageViewRecipe.transitionName = imageUrl

            imageViewRecipe.loadRelativeUrl(imageUrl) {
                crossfade(true)
                placeholder(R.drawable.listing_img_placeholder)
                transformations(RoundedCornersTransformation(4f.px))
            }

            container.setOnClickListener {
                onClick?.invoke(recipe, binding.imageViewRecipe)
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