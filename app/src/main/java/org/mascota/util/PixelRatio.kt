package org.mascota.util

import android.app.Application
import androidx.annotation.Px
import org.mascota.MascotaApplication
import kotlin.math.roundToInt

class PixelRatio(private val app: Application) {
    private val displayMetrics
        get() = app.resources.displayMetrics

    @Px
    fun toPixel(dp: Int) = (dp * displayMetrics.density).roundToInt()
}

val Number.dp: Int
    get() = MascotaApplication.pixelRatio.toPixel(this.toInt())