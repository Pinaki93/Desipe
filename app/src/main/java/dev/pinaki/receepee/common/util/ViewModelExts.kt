package dev.pinaki.receepee.common.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

inline fun ViewModel.ioScope(crossinline block: (suspend () -> Unit)) {
    viewModelScope.launch(Dispatchers.IO) {
        block()
    }
}