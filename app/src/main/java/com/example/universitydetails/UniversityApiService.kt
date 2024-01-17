package com.example.universitydetails

import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityApiService {
    @GET("search")
    suspend fun getUniversities(@Query("country") country: String): List<UniversityItem>
}
