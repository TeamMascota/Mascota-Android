package org.mascota.util.extension

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.replace(@IdRes frameId: Int, fragment: androidx.fragment.app.Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(frameId, fragment, null)
        .commit()
}