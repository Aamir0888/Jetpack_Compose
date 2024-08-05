package com.example.jetpackcompose.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.utilities.PreferencesHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    private val _name = MutableStateFlow(PreferencesHelper.getString(PreferencesHelper.NAME) ?: "Your name")
    val name: StateFlow<String> get() = _name

    private val _email = MutableStateFlow(PreferencesHelper.getString(PreferencesHelper.EMAIL) ?: "Your email")
    val email: StateFlow<String> get() = _email

    private val _bio = MutableStateFlow(
        PreferencesHelper.getString(PreferencesHelper.DESCRIPTION) ?: "Your description or bio")
    val bio: StateFlow<String> get() = _bio

    fun updateName(newName: String) {
        _name.value = newName
        viewModelScope.launch {
            PreferencesHelper.setString(PreferencesHelper.NAME, newName)
        }
    }

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
        viewModelScope.launch {
            PreferencesHelper.setString(PreferencesHelper.EMAIL, newEmail)
        }
    }

    fun updateBio(newBio: String) {
        _bio.value = newBio
        viewModelScope.launch {
            PreferencesHelper.setString(PreferencesHelper.DESCRIPTION, newBio)
        }
    }
}