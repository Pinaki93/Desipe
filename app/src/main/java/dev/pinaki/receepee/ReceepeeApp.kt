package dev.pinaki.receepee

import android.app.Application
import dev.pinaki.receepee.common.timber.ProductionTimberTree
import dev.pinaki.receepee.di.repositoryModule
import dev.pinaki.receepee.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class ReceepeeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ProductionTimberTree())
        }

        startKoin {
            androidContext(this@ReceepeeApp)
            modules(
                repositoryModule,
                viewModelModule
            )
        }
    }

}