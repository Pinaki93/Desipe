package dev.pinaki.desipe.feature.darktheme

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.pinaki.desipe.R
import dev.pinaki.desipe.common.ds.DarkThemeMode
import dev.pinaki.desipe.common.ds.Empty
import dev.pinaki.desipe.common.ds.OneTimeEvent
import dev.pinaki.desipe.common.themeswitcher.ThemeSwitchingManager
import dev.pinaki.desipe.data.repository.ThemeRepository

@Suppress("MemberVisibilityCanBePrivate")
class DarkThemeModeChooserViewModel constructor(
    val themeRepository: ThemeRepository,
    val themeSwitchingManager: ThemeSwitchingManager,
    val osVersion: Int
) : ViewModel() {

    private val _modes = MutableLiveData<List<Int>>()
    val modes: LiveData<List<Int>> = _modes

    private val _dismiss = MutableLiveData<OneTimeEvent<Empty>>()
    val dismiss: LiveData<OneTimeEvent<Empty>> = _dismiss

    init {
        _modes.value = mutableListOf(
            R.string.dark_theme_mode_disable,
            R.string.dark_theme_mode_enable,
            R.string.dark_theme_mode_auto
        ).apply {
            if(osVersion >= Build.VERSION_CODES.Q){
                add(R.string.dark_theme_mode_system)
            }
        }
    }

    fun onThemeChosen(index: Int) {
        val themeMode = DarkThemeMode.values()[index]
        themeRepository.setCurrentDarkThemeMode(themeMode)
        themeSwitchingManager.apply()

        _dismiss.value = OneTimeEvent(Empty)
    }
}