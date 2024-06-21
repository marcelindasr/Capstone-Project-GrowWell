package com.budiwira.myapplication66.remote.retrofit

import com.budiwira.myapplication66.remote.response.LoginRequest
import com.budiwira.myapplication66.remote.response.LoginResponse
import com.budiwira.myapplication66.remote.response.SignUpRequest
import com.budiwira.myapplication66.remote.response.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("signup")
    fun signUp(@Body signUpRequest: SignUpRequest): Call<SignUpResponse>
}