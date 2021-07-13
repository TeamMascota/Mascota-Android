package org.mascota.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object MascotaSharedPreference {
    private const val USER_ID = "USER_ID"
    private const val PART = "PART"

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getUserId(): String {
        return preferences.getString(USER_ID, "60ed286ee8071256280e0fe7") ?: "60ed286ee8071256280e0fe7"
    }

    fun setUserId(value: String) {
        preferences.edit().putString(USER_ID, value).apply()
    }

    fun getPart(): Int = preferences.getInt(PART, 1)

    fun setPart(value: Int) {
        preferences.edit{putInt(PART, value)}
    }
}