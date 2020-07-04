package dev.pinaki.receepee.feature.detail.heading.section

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.pinaki.receepee.databinding.HeadingViewBinding

class HeadingViewHolder private constructor(
    private val binding: HeadingViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(title: String) {
        binding.textViewTitle.text = title
    }

    companion object {
        @JvmStatic
        fun from(parent: ViewGroup): HeadingViewHolder {
            return HeadingViewHolder(
                HeadingViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}