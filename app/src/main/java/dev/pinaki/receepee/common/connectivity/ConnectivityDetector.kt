package dev.pinaki.receepee.common.connectivity

interface ConnectivityDetector {
    fun isConnected(): Boolean
}