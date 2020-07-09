package dev.pinaki.desipe.feature.detail.heading.section

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HeadingAdapter(private val heading: String) : RecyclerView.Adapter<HeadingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadingViewHolder {
        return HeadingViewHolder.from(
            parent
        )
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: HeadingViewHolder, position: Int) {
        holder.bind(heading)
    }

}