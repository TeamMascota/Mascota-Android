package org.mascota.di

import org.koin.dsl.module
import org.mascota.data.remote.datasource.content.RemoteContentDataSource
import org.mascota.data.remote.datasource.diary.RemoteDiaryDataSource
import org.mascota.data.remote.datasource.home.RemoteHomeDataSource
import org.mascota.data.remote.datasource.profile.RemoteProfileDataSource
import org.mascota.data.remote.datasource.rainbow.RemoteRainbowDataSource
import org.mascota.data.remote.datasource.user.RemoteUserDataSource
import org.mascota.data.repository.calendar.CalendarRepository
import org.mascota.data.repository.calendar.CalendarRepositoryImpl
import org.mascota.data.repository.content.ContentRepository
import org.mascota.data.repository.content.ContentRepositoryImpl
import org.mascota.data.repository.diary.DiaryRepository
import org.mascota.data.repository.diary.DiaryRepositoryImpl
import org.mascota.data.repository.home.HomeRepository
import org.mascota.data.repository.home.HomeRepositoryImpl
import org.mascota.data.repository.profile.ProfileRepository
import org.mascota.data.repository.profile.ProfileRepositoryImpl
import org.mascota.data.repository.rainbow.RainbowRepository
import org.mascota.data.repository.rainbow.RainbowRepositoryImpl
import org.mascota.data.repository.user.UserRepository
import org.mascota.data.repository.user.UserRepositoryImpl

val repositoryModule = module {
    single<CalendarRepository> { CalendarRepositoryImpl(get()) }
    single<RainbowRepository> { RainbowRepositoryImpl(get()) }
    single<ContentRepository> { ContentRepositoryImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<DiaryRepository> { DiaryRepositoryImpl(get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
}