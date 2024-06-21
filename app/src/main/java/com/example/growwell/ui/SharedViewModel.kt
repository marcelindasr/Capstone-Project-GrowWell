package com.example.growwell.ui.akun

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> get() = _fullName

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _birthDate = MutableLiveData<String>()
    val birthDate: LiveData<String> get() = _birthDate

    fun setFullName(name: String) {
        _fullName.value = name
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setBirthDate(birthDate: String) {
        _birthDate.value = birthDate
    }
}
