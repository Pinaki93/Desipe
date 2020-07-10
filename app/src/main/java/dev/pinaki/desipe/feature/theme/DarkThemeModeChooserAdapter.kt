package dev.pinaki.desipe.feature.theme

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.pinaki.desipe.common.util.StringDiffUtilCallback

class DarkThemeModeChooserAdapter :
    ListAdapter<String, DarkThemeModeChooserViewHolder>(StringDiffUtilCallback) {

    var clickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DarkThemeModeChooserViewHolder {
        return DarkThemeModeChooserViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DarkThemeModeChooserViewHolder, position: Int) {
        holder.setItem(getItem(position), clickListener)
    }
}