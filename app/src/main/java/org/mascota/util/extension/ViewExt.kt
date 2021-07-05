package org.mascota.util.extension

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.Px
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

fun AppCompatActivity.replace(@IdRes frameId: Int, fragment: androidx.fragment.app.Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(frameId, fragment, null)
        .commit()
}

@Px
fun View.px(dp: Int) = (dp * resources.displayMetrics.density).roundToInt()