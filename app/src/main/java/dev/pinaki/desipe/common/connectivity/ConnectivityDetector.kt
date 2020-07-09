package dev.pinaki.desipe.common.connectivity

interface ConnectivityDetector {
    fun isConnected(): Boolean
}