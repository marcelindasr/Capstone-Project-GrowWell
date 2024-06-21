package com.budiwira.myapplication66.remote.response

data class SignUpRequest(
    val fullName: String,
    val birthDate: String,
    val email: String,
    val password: String
)
