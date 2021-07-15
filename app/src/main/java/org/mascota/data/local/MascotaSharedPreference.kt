package org.mascota.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object MascotaSharedPreference {
    private const val USER_ID = "USER_ID"
    private const val PET_ID = "PET_ID"
    private const val PART = "PART"
    private const val IS_PROFILE_CREATE = "IS_PROFILE_CREATE"

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getUserId(): String {
        return preferences.getString(USER_ID, "60edf1b7a95c4a2a8c64c6ba") ?: "60edf1b7a95c4a2a8c64c6ba"
    }

    fun getPetId(): String {
        return preferences.getString(USER_ID, "60edf6e5e5003a744892ce39") ?: "60edf6e5e5003a744892ce39"
    }

    fun setPetId(value: String) {
        preferences.edit().putString(PET_ID, value).apply()

    }

    fun setUserId(value: String) {
        preferences.edit().putString(USER_ID, value).apply()
    }

    fun getIsProfileCreate(value: Boolean) {
        preferences.getBoolean(IS_PROFILE_CREATE, false)
    }

    fun setIsProfileCreate(value: Boolean) {
        preferences.edit().putBoolean(USER_ID, value).apply()
    }

    fun getPart(): Int = preferences.getInt(PART, 1)

    fun setPart(value: Int) {
        preferences.edit{putInt(PART, value)}
    }
}