package dev.pinaki.receepee.feature.detail.steps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.pinaki.receepee.databinding.RecipeStepViewBinding

class RecipeStepsViewHolder private constructor(private val binding: RecipeStepViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(step: Int, stepText: String, isLast: Boolean) {
        with(binding) {
            textViewStepNumber.text = step.toString()
            textViewContent.text = stepText
            recipeStepLinkBottom.visibility = if (!isLast) View.VISIBLE else View.INVISIBLE
            recipeStepLinkTop.visibility = if (step != 1) View.VISIBLE else View.INVISIBLE
        }
    }

    companion object {
        @JvmStatic
        fun from(parent: ViewGroup): RecipeStepsViewHolder {
            return RecipeStepsViewHolder(
                RecipeStepViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}