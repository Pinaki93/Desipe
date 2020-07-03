package dev.pinaki.receepee.common.coroutines

import kotlinx.coroutines.Dispatchers

class DispatcherProviderImpl constructor() : DispatcherProvider {
    override fun io() = Dispatchers.IO

    override fun main() = Dispatchers.Main

    override fun default() = Dispatchers.Default
}