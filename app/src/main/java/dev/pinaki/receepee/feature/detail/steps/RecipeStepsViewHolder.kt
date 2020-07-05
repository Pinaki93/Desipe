package dev.pinaki.receepee.feature.detail.steps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.pinaki.receepee.R
import dev.pinaki.receepee.databinding.RecipeStepViewBinding

class RecipeStepsViewHolder private constructor(private val binding: RecipeStepViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(step: Int, stepText: String, totalSteps: Int) {
        with(binding) {
            textViewStepNumber.text =
                textViewStepNumber.context.getString(R.string.step_progress, step, totalSteps)
            textViewContent.text = stepText
            progressBarRecipeStep.progress = ((step * 100) / totalSteps)
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