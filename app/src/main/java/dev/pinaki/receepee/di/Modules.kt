package dev.pinaki.receepee.di

import androidx.room.Room
import com.squareup.moshi.Moshi
import dev.pinaki.receepee.BuildConfig
import dev.pinaki.receepee.data.repository.RecipeRepository
import dev.pinaki.receepee.data.repository.RecipeRepositoryImpl
import dev.pinaki.receepee.data.source.local.DesipeDatabase
import dev.pinaki.receepee.data.source.remote.RecipeApiService
import dev.pinaki.receepee.feature.detail.DetailsViewModel
import dev.pinaki.receepee.feature.listing.RecipeListingViewModel
import dev.pinaki.receepee.helper.moshi.DesipeMoshiHelper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    single<RecipeRepository> { RecipeRepositoryImpl() }
}

private val viewModelModule = module {
    viewModel { RecipeListingViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}

val desipeAppModules =
    arrayOf(configModule, databaseModule, httpModule, repositoryModule, viewModelModule)