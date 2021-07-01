package org.mascota

import android.app.Application
import org.mascota.di.dataSourceModule
import org.mascota.di.viewModelModule
import org.mascota.util.extension.setUpKoin

class MascotaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setUpKoin(
            this,
            viewModelModule,
            dataSourceModule
        )
    }
}