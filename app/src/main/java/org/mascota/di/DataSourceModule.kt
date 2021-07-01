package org.mascota.di

import org.koin.dsl.module
import org.mascota.ui.view.calendar.data.datasource.AuthorDataSource
import org.mascota.ui.view.calendar.data.datasource.LocalAuthorDataSource

val dataSourceModule = module {
    single<AuthorDataSource> { LocalAuthorDataSource() }
}