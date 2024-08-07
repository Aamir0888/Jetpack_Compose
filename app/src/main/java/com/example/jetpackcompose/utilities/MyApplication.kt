package com.example.jetpackcompose.utilities

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize PreferencesHelper
        PreferencesHelper.init(this)
    }
}