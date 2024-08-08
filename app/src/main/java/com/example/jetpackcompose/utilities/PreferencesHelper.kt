package com.example.jetpackcompose.utilities

import android.content.Context
import android.content.SharedPreferences

object PreferencesHelper {
    private lateinit var preferences: SharedPreferences
    const val NAME = "NAME"
    const val EMAIL = "EMAIL"
    const val DESCRIPTION = "DESCRIPTION"
    const val IS_LOGIN = "is_login"
    const val TOKEN = "token"
    const val USER_ID = "user_id"

    fun init(context: Context) {
        preferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }

    fun getString(key: String): String? {
        return preferences.getString(key, null)
    }

    fun setString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    fun setBoolean(key: String, value: Boolean){
        preferences.edit().putBoolean(key, value).apply()
    }
}