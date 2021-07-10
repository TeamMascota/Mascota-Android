package org.mascota

import android.app.Application
import org.koin.android.ext.android.inject
import org.mascota.data.local.MascotaSharedPreference
import org.mascota.di.dataSourceModule
import org.mascota.di.singletonModule
import org.mascota.di.viewModelModule
import org.mascota.util.PixelRatio
import org.mascota.util.extension.setUpKoin

class MascotaApplication : Application() {
    private val pixelRatio : PixelRatio by inject()

    override fun onCreate() {
        super.onCreate()

        setUpKoin(
            this,
            viewModelModule,
            dataSourceModule,
            singletonModule
        )

        MascotaApplication.pixelRatio = pixelRatio
        MascotaSharedPreference.init(applicationContext)
    }

    companion object {
        lateinit var pixelRatio: PixelRatio
    }
}