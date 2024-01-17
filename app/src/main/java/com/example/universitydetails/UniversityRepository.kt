package com.example.universitydetails

import android.util.Log

class UniversityRepository {
    private val universityApiService = RetrofitInstance.universityApiService

    suspend fun getUniversities(): List<UniversityItem> {
        try {
            val universities = universityApiService.getUniversities("United States")
            Log.d("UniversityRepository", "Fetched universities: $universities")
            return universities
        } catch (e: Exception) {
            Log.e("UniversityRepository", "Error fetching universities", e)
            throw e
        }
    }
}