package com.example.universitydetails

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://universities.hipolabs.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val universityApiService: UniversityApiService by lazy {
        retrofit.create(UniversityApiService::class.java)
    }
}