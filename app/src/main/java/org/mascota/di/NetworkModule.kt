package org.mascota.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.mascota.data.remote.api.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single{
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }
    single<Retrofit>{
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://13.209.129.17:5000/api/")
            .build()
    }
    single<CalendarService> {
        get<Retrofit>().create(CalendarService::class.java)
    }
    single<ContentService> {
        get<Retrofit>().create(ContentService::class.java)
    }
    single<DiaryService> {
        get<Retrofit>().create(DiaryService::class.java)
    }
    single<HomeService> {
        get<Retrofit>().create(HomeService::class.java)
    }
    single<ProfileService> {
        get<Retrofit>().create(ProfileService::class.java)
    }
    single<RainbowService> {
        get<Retrofit>().create(RainbowService::class.java)
    }
    single<UserService> {
        get<Retrofit>().create(UserService::class.java)
    }
}