package dev.pinaki.receepee.feature.detail.heading.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.pinaki.receepee.databinding.RecipeHeadingViewBinding

class RecipeHeadingViewHolder private constructor(private val binding: RecipeHeadingViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(recipeHeading: String, recipeDescription: String) {
        with(binding) {
            textViewTitle.text = recipeHeading
            textViewContent.text = recipeDescription
        }
    }

    companion object {
        @JvmStatic
        fun from(parent: ViewGroup): RecipeHeadingViewHolder {
            return RecipeHeadingViewHolder(
                RecipeHeadingViewBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }

}