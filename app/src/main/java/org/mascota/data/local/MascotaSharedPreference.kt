package org.mascota.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object MascotaSharedPreference {
    private const val PART = "PART"

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getPart(): Int = preferences.getInt(PART, 1)

    fun setPart(value: Int) {
        preferences.edit{putInt(PART, value)}
    }
}