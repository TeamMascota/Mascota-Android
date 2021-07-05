package org.mascota.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.mascota.util.PixelRatio

val singletonModule = module {
    single { PixelRatio(androidApplication()) }
}