package dev.pinaki.receepee.feature.detail.steps

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.pinaki.receepee.common.util.StringDiffUtilCallback


class RecipeStepsAdapter : ListAdapter<String, RecipeStepsViewHolder>(StringDiffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeStepsViewHolder {
        return RecipeStepsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecipeStepsViewHolder, position: Int) {
        holder.bind(position + 1, getItem(position), itemCount)
    }
}