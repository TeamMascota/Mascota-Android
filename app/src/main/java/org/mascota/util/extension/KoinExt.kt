package org.mascota.util.extension

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun setUpKoin(
    context : Context,
    vararg module : Module
) {
    startKoin {
        androidContext(context)
        modules(*module)
    }
}