package dev.pinaki.desipe.di

import androidx.room.Room
import com.squareup.moshi.Moshi
import dev.pinaki.desipe.BuildConfig
import dev.pinaki.desipe.common.connectivity.ConnectivityDetector
import dev.pinaki.desipe.common.connectivity.ConnectivityDetectorImpl
import dev.pinaki.desipe.common.coroutines.DispatcherProvider
import dev.pinaki.desipe.common.coroutines.DispatcherProviderImpl
import dev.pinaki.desipe.data.repository.RecipeRepository
import dev.pinaki.desipe.data.repository.RecipeRepositoryImpl
import dev.pinaki.desipe.data.source.local.DesipeDatabase
import dev.pinaki.desipe.data.source.remote.RecipeApiService
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

private val connectivityDetectorModule = module {
    single<ConnectivityDetector> { ConnectivityDetectorImpl(androidApplication()) }
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
    single<RecipeRepository> { RecipeRepositoryImpl(get(), get()) }
}

private val dispatcherProviderModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }
}

private val viewModelModule = module {
    viewModel { RecipeListingViewModel(get(), get(), get()) }
    viewModel { DetailsViewModel(get()) }
}

val desipeAppModules =
    arrayOf(
        configModule,
        connectivityDetectorModule,
        databaseModule,
        httpModule,
        repositoryModule,
        dispatcherProviderModule,
        viewModelModule
    )