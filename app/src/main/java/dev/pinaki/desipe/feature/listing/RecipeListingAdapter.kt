package dev.pinaki.desipe.feature.listing

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.pinaki.desipe.data.model.Recipe

class RecipeListingAdapter : ListAdapter<Recipe, RecipeListingViewHolder>(recipeDiffCallback) {

    var onClick: ((Recipe, ImageView) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListingViewHolder {
        return RecipeListingViewHolder.from(parent)
    }

    override fun onBindViewHolder(holderRecipeListing: RecipeListingViewHolder, position: Int) {
        holderRecipeListing.bind(getItem(position), onClick)
    }

    companion object {
        val recipeDiffCallback = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }
        }
    }
}