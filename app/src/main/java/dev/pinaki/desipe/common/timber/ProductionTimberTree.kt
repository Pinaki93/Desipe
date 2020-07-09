package dev.pinaki.desipe.common.timber

import timber.log.Timber

class ProductionTimberTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // in production applciations, we can use crashalytics here
    }
}