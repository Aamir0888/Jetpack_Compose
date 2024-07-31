package com.example.jetpackcompose.utilities

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize PreferencesHelper
        PreferencesHelper.init(this)
    }
}