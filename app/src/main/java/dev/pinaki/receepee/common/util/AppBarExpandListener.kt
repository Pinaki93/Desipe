package dev.pinaki.receepee.common.util

import com.google.android.material.appbar.AppBarLayout

abstract class AppBarExpandListener : AppBarLayout.OnOffsetChangedListener {

    var showing = true
    var scrollRange = -1

    abstract fun onExpanded(expanded: Boolean)

    override fun onOffsetChanged(barLayout: AppBarLayout?, verticalOffset: Int) {
        if (barLayout == null) return

        if (scrollRange == -1) {
            scrollRange = barLayout.totalScrollRange
        }
        if (scrollRange + verticalOffset == 0) {
            //collapsed
            onExpanded(true)
            showing = true
        } else if (showing) {
            onExpanded(false)
            showing = false
        }
    }
}