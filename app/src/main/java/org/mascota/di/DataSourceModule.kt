package org.mascota.di

import org.koin.dsl.module
import org.mascota.data.remote.datasource.calendar.CalendarDataSource
import org.mascota.data.remote.datasource.calendar.RemoteCalendarDataSource
import org.mascota.data.remote.datasource.content.ContentDataSource
import org.mascota.data.remote.datasource.content.RemoteContentDataSource
import org.mascota.data.remote.datasource.diary.DiaryDataSource
import org.mascota.data.remote.datasource.diary.RemoteDiaryDataSource
import org.mascota.data.remote.datasource.home.HomeDataSource
import org.mascota.data.remote.datasource.home.RemoteHomeDataSource
import org.mascota.data.remote.datasource.profile.ProfileDataSource
import org.mascota.data.remote.datasource.profile.RemoteProfileDataSource
import org.mascota.data.remote.datasource.rainbow.RainbowDataSource
import org.mascota.data.remote.datasource.rainbow.RemoteRainbowDataSource
import org.mascota.data.remote.datasource.user.RemoteUserDataSource
import org.mascota.data.remote.datasource.user.UserDataSource
import org.mascota.ui.view.calendar.data.datasource.AuthorDataSource
import org.mascota.ui.view.calendar.data.datasource.LocalAuthorDataSource
import org.mascota.ui.view.calendar.data.datasource.LocalTempCalendarDataSource
import org.mascota.ui.view.calendar.data.datasource.TempCalendarDataSource
import org.mascota.ui.view.content.detail.data.datasource.ContentDetailDataSource
import org.mascota.ui.view.content.detail.data.datasource.LocalContentDetailDataSource
import org.mascota.ui.view.home.data.datasource.HomeBookDataSource
import org.mascota.ui.view.home.data.datasource.LocalHomeBookDataSource
import org.mascota.ui.view.rainbow.farewell.data.datasource.FarewellDataSource
import org.mascota.ui.view.rainbow.farewell.data.datasource.LocalFarewellDataSource


val dataSourceModule = module {
    single<AuthorDataSource> { LocalAuthorDataSource() }
    single<HomeBookDataSource> { LocalHomeBookDataSource() }
    single<ContentDetailDataSource> { LocalContentDetailDataSource() }
    single<FarewellDataSource> { LocalFarewellDataSource() }

    //remote
    single<CalendarDataSource> { RemoteCalendarDataSource(get()) }
    single<RainbowDataSource> { RemoteRainbowDataSource(get()) }
    single<ContentDataSource> { RemoteContentDataSource(get()) }
    single<HomeDataSource> { RemoteHomeDataSource(get()) }
    single<DiaryDataSource> { RemoteDiaryDataSource(get()) }
    single<ProfileDataSource> { RemoteProfileDataSource(get()) }
    single<UserDataSource> { RemoteUserDataSource(get()) }
}