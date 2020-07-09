/*
 * MIT License
 *
 * Copyright (c) 2020 Pinaki Acharya
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dev.pinaki.desipe.common.util

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