package org.mascota.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.mascota.ui.viewmodel.*

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { CalendarViewModel(get(), get(), get()) }
    viewModel { RainbowViewModel(get(), get(), get()) }
    viewModel { ContentViewModel(get(), get()) }
    viewModel { DiaryViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { UserViewModel(get()) }
}