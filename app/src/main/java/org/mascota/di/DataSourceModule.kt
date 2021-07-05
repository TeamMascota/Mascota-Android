package org.mascota.di

import org.koin.dsl.module
import org.mascota.ui.view.calendar.data.datasource.AuthorDataSource
import org.mascota.ui.view.calendar.data.datasource.CalendarDataSource
import org.mascota.ui.view.calendar.data.datasource.LocalAuthorDataSource
import org.mascota.ui.view.calendar.data.datasource.LocalCalendarDataSource
import org.mascota.ui.view.home.data.datasource.HomeBookDataSource
import org.mascota.ui.view.home.data.datasource.LocalHomeBookDataSource

val dataSourceModule = module {
    single<AuthorDataSource> { LocalAuthorDataSource() }
    single<CalendarDataSource> { LocalCalendarDataSource() }
    single<HomeBookDataSource> { LocalHomeBookDataSource() }
}