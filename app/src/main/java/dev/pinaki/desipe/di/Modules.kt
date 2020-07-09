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

package dev.pinaki.desipe.di

import android.os.Build
import androidx.room.Room
import com.squareup.moshi.Moshi
import dev.pinaki.desipe.BuildConfig
import dev.pinaki.desipe.common.connectivity.ConnectivityDetector
import dev.pinaki.desipe.common.connectivity.ConnectivityDetectorImpl
import dev.pinaki.desipe.common.coroutines.DispatcherProvider
import dev.pinaki.desipe.common.coroutines.DispatcherProviderImpl
import dev.pinaki.desipe.common.themeswitcher.ThemeSwitchingManager
import dev.pinaki.desipe.common.themeswitcher.ThemeSwitchingManagerImpl
import dev.pinaki.desipe.data.repository.RecipeRepository
import dev.pinaki.desipe.data.repository.ThemeRepository
import dev.pinaki.desipe.data.repository.impl.RecipeRepositoryImpl
import dev.pinaki.desipe.data.repository.impl.ThemeRepositoryImpl
import dev.pinaki.desipe.data.source.local.DesipeDatabase
import dev.pinaki.desipe.data.source.local.sharedprefs.SharedPreferencesManager
import dev.pinaki.desipe.data.source.local.sharedprefs.SharedPreferencesManagerImpl
import dev.pinaki.desipe.data.source.remote.RecipeApiService
import dev.pinaki.desipe.feature.darktheme.DarkThemeModeChooserViewModel
import dev.pinaki.desipe.feature.detail.DetailsViewModel
import dev.pinaki.desipe.feature.listing.RecipeListingViewModel
import dev.pinaki.desipe.helper.moshi.DesipeMoshiHelper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val configModule = module {
    single(named(InjectionConstants.KEY_DATABASE_NAME)) { BuildConfig.DATABASE_NAME }
    single(named(InjectionConstants.KEY_IS_DEBUG_BUILD)) { BuildConfig.DEBUG }
    single(named(InjectionConstants.KEY_BASE_URL)) { BuildConfig.SERVER_URL }
    single(named(InjectionConstants.KEY_PREFERENCES_NAME)) { BuildConfig.SHARED_PREFS_NAME }
    single(named(InjectionConstants.KEY_OS_VERSION)) { Build.VERSION.SDK_INT }
}

private val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(), DesipeDatabase::class.java, get(
                named(InjectionConstants.KEY_DATABASE_NAME)
            )
        ).build()
    }

    single { get<DesipeDatabase>().recipeDao() }
}

private val sharedPreferencesModule = module {
    single<SharedPreferencesManager> {
        SharedPreferencesManagerImpl(
            androidApplication(),
            get(named(InjectionConstants.KEY_PREFERENCES_NAME))
        )
    }
}

private val httpModule = module {
    // okhttp dependencies
    single<Interceptor> {
        HttpLoggingInterceptor().apply {
            level = if (get(named(InjectionConstants.KEY_IS_DEBUG_BUILD))) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single { OkHttpClient.Builder().addInterceptor(get<Interceptor>()).build() }

    // retrofit dependencies
    single { DesipeMoshiHelper.moshi }
    single<Converter.Factory> { MoshiConverterFactory.create(get<Moshi>()) }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(InjectionConstants.KEY_BASE_URL)))
            .client(get())
            .addConverterFactory(get())
            .build()
    }

    single { get<Retrofit>().create(RecipeApiService::class.java) }
}

private val repositoryModule = module {
    single<RecipeRepository> {
        RecipeRepositoryImpl(
            get(),
            get()
        )
    }

    single<ThemeRepository> {
        ThemeRepositoryImpl(get())
    }
}

private val dispatcherProviderModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }
}

private val viewModelModule = module {
    viewModel {
        RecipeListingViewModel(get(), get(), get())
    }
    viewModel { DetailsViewModel(get()) }
    viewModel {
        DarkThemeModeChooserViewModel(
            get(),
            get(),
            get(named(InjectionConstants.KEY_OS_VERSION))
        )
    }
}

private val connectivityDetectorModule = module {
    single<ConnectivityDetector> { ConnectivityDetectorImpl(androidApplication()) }
}

private val themeSwitchingManagerModule = module {
    single<ThemeSwitchingManager> { ThemeSwitchingManagerImpl(get()) }
}

val desipeAppModules =
    arrayOf(
        configModule,
        connectivityDetectorModule,
        databaseModule,
        httpModule,
        sharedPreferencesModule,
        repositoryModule,
        dispatcherProviderModule,
        viewModelModule,
        themeSwitchingManagerModule
    )