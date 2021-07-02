package org.mascota.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.mascota.ui.viewmodel.CalendarViewModel
import org.mascota.ui.viewmodel.HomeViewModel
import org.mascota.ui.viewmodel.RainbowViewModel

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { CalendarViewModel(get()) }
    viewModel { RainbowViewModel() }
}