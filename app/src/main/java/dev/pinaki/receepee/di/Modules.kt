package dev.pinaki.receepee.di

import androidx.room.Room
import dev.pinaki.receepee.BuildConfig
import dev.pinaki.receepee.data.repository.RecipeRepository
import dev.pinaki.receepee.data.repository.RecipeRepositoryImpl
import dev.pinaki.receepee.data.source.local.DesipeDatabase
import dev.pinaki.receepee.feature.detail.DetailsViewModel
import dev.pinaki.receepee.feature.listing.RecipeListingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val configModule = module {
    single(named(InjectionConstants.KEY_DATABASE_NAME)) { BuildConfig.DATABASE_NAME }
}

private val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(), DesipeDatabase::class.java, get(
                named(InjectionConstants.KEY_DATABASE_NAME)
            )
        ).build()
    }

    single { get<DesipeDatabase>().recipeDao()  }
}

private val repositoryModule = module {
    single<RecipeRepository> { RecipeRepositoryImpl() }
}

private val viewModelModule = module {
    viewModel { RecipeListingViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}

val desipeAppModules = arrayOf(configModule, databaseModule, repositoryModule, viewModelModule)