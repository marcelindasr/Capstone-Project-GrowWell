package com.example.growwell.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApiService {
    @GET("place/nearbysearch/json")
    fun getNearbyPlaces(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("type") type: String,
        @Query("key") apiKey: String
    ): Call<PlacesResponse>

    @GET("place/textsearch/json")
    fun searchPlace(
        @Query("query") query: String,
        @Query("key") apiKey: String
    ): Call<PlacesResponse>
}
