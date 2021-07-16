package org.mascota.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object MascotaSharedPreference {
    private const val USER_ID = "USER_ID"
    private const val PET_ID = "PET_ID"
    private const val PART = "PART"
    private const val IS_PROFILE_CREATE = "IS_PROFILE_CREATE"
    private const val IS_LOGIN = "LOGIN"

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getUserId(): String {
        return preferences.getString(USER_ID, "") ?: ""
    }

    fun getPetId(): String {
        return preferences.getString(PET_ID, "") ?: ""
    }

    fun setPetId(value: String) {
        preferences.edit().putString(PET_ID, value).apply()
    }

    fun setUserId(value: String) {
        preferences.edit().putString(USER_ID, value).apply()
    }

    fun getIsProfileCreate() = preferences.getBoolean(IS_PROFILE_CREATE, false)

    fun setIsProfileCreate(value: Boolean) {
        preferences.edit().putBoolean(IS_PROFILE_CREATE, value).apply()
    }

    fun getLogin(): Boolean = preferences.getBoolean(IS_LOGIN, false)

    fun setLogin(value: Boolean) {
        preferences.edit{putBoolean(IS_LOGIN, value)}
    }

    fun getPart(): Int = preferences.getInt(PART, 1)

    fun setPart(value: Int) {
        preferences.edit{putInt(PART, value)}
    }
}