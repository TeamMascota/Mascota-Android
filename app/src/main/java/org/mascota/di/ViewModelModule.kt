package org.mascota.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.mascota.ui.viewmodel.*

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { CalendarViewModel(get(), get()) }
    viewModel { RainbowViewModel(get(), get()) }
    viewModel { ContentViewModel(get()) }
    viewModel { DiaryViewModel(get(), get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { UserViewModel(get()) }
}