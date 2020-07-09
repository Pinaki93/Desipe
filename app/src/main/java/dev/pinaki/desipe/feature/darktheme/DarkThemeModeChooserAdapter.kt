package dev.pinaki.desipe.feature.darktheme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.pinaki.desipe.common.util.StringDiffUtilCallback
import dev.pinaki.desipe.databinding.DarkModeChooserItemBinding

class DarkThemeModeChooserAdapter :
    ListAdapter<String, DarkThemeModeChooserAdapter.ViewHolder>(StringDiffUtilCallback) {

    var clickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(getItem(position), clickListener)
    }

    class ViewHolder private constructor(
        private val binding: DarkModeChooserItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(item: String, clickListener: ((Int) -> Unit)?) {
            with(binding.tvContent) {
                text = item
                setOnClickListener {
                    clickListener?.invoke(bindingAdapterPosition)
                }
            }
            binding.tvContent.text = item
        }

        companion object {
            @JvmStatic
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    DarkModeChooserItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    }
}