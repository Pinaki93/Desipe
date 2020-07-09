package dev.pinaki.desipe

import android.app.Application
import dev.pinaki.desipe.common.timber.ProductionTimberTree
import dev.pinaki.desipe.di.desipeAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class DesipeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else ProductionTimberTree())

        startKoin {
            androidContext(this@DesipeApp)
            modules(*desipeAppModules)
        }
    }
}