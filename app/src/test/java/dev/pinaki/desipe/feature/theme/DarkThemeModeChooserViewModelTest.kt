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

package dev.pinaki.desipe.feature.theme

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.pinaki.desipe.R
import dev.pinaki.desipe.common.ds.DarkThemeMode
import dev.pinaki.desipe.common.themeswitcher.ThemeSwitchingManager
import dev.pinaki.desipe.data.repository.ThemeRepository
import dev.pinaki.desipe.testutil.MainCoroutineRule
import dev.pinaki.desipe.testutil.getValue
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for [DarkThemeModeChooserViewModel]
 *
 * - below API 29, option to follow system settings is not shown to users
 * - on API 29 & above, option to follow system settings is not shown to users
 * - choosing a dark theme mode saves and updates the UI with the correct mode
 */
@ExperimentalCoroutinesApi
class DarkThemeModeChooserViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var themeRepository: ThemeRepository

    @MockK
    lateinit var themeSwitchingManager: ThemeSwitchingManager

    lateinit var viewModel: DarkThemeModeChooserViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `below API 29, option to follow system settings is not shown to users`() {
        viewModel = DarkThemeModeChooserViewModel(themeRepository, themeSwitchingManager, 23)

        val modes = getValue(viewModel.modes)
        assert(!modes.contains(R.string.dark_theme_mode_system))
        assert(modes.size == 3)
    }

    @Test
    fun `on API 29 & above, option to follow system settings is not shown to users`() {
        viewModel = DarkThemeModeChooserViewModel(themeRepository, themeSwitchingManager, 29)

        val modes = getValue(viewModel.modes)
        assert(modes.contains(R.string.dark_theme_mode_system))
        assert(modes.size == 4)
    }

    @Test
    fun `choosing a dark theme mode saves and updates the UI with the correct mode`() {
        viewModel = DarkThemeModeChooserViewModel(themeRepository, themeSwitchingManager, 29)

        for(i in (0..3)){
            viewModel.onThemeChosen(i)
            verify { themeRepository.setCurrentDarkThemeMode(DarkThemeMode.values()[i]) }
            verify { themeSwitchingManager.apply() }
            assertNotNull(getValue(viewModel.dismiss).getContentIfNotHandled())
        }
    }
}