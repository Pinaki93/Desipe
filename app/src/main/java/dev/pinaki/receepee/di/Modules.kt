package dev.pinaki.receepee.di

import dev.pinaki.receepee.data.repository.RecipeRepository
import dev.pinaki.receepee.data.repository.RecipeRepositoryImpl
import dev.pinaki.receepee.feature.listing.RecipeListingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val receepeeModule = module {
    single<RecipeRepository> { RecipeRepositoryImpl() }
}

val viewModelModule = module {
    viewModel { RecipeListingViewModel(get()) }
}