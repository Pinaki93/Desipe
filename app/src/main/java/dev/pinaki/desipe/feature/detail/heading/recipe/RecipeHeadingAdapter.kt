package dev.pinaki.desipe.feature.detail.heading.recipe

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecipeHeadingAdapter : RecyclerView.Adapter<RecipeHeadingViewHolder>() {

    var headingAndDescription: Pair<String, String>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHeadingViewHolder {
        return RecipeHeadingViewHolder.from(parent)
    }

    override fun getItemCount() = if (headingAndDescription != null) 1 else 0

    override fun onBindViewHolder(holder: RecipeHeadingViewHolder, position: Int) {
        holder.bind(headingAndDescription!!.first, headingAndDescription!!.second)
    }

}