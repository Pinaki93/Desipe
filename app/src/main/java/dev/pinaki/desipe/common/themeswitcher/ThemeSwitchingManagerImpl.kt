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

package dev.pinaki.desipe.common.themeswitcher

import androidx.appcompat.app.AppCompatDelegate
import dev.pinaki.desipe.common.ds.DarkThemeMode
import dev.pinaki.desipe.data.repository.ThemeRepository
import javax.inject.Inject

class ThemeSwitchingManagerImpl @Inject constructor(
    private val themeRepository: ThemeRepository
) : ThemeSwitchingManager {

    override fun apply() {
        val nightMode = when (themeRepository.getCurrentDarkThemeMode()) {
            DarkThemeMode.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            DarkThemeMode.DARK -> AppCompatDelegate.MODE_NIGHT_YES
            DarkThemeMode.AUTO -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            DarkThemeMode.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            else -> -1
        }

        if (nightMode == -1) return

        AppCompatDelegate.setDefaultNightMode(nightMode)
    }
}