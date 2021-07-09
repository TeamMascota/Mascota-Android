package org.mascota.util

import android.app.Activity
import androidx.annotation.ColorInt

object StatusBarUtil {
    fun Activity.setStatusBarColor(@ColorInt color: Int) {
        this.window?.run { statusBarColor = color }
    }
}