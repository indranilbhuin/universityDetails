package com.example.universitydetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UniversityViewModel : ViewModel() {
    private val repository = UniversityRepository()

    private val _universities = MutableLiveData<List<UniversityItem>>()
    val universityData: LiveData<List<UniversityItem>> = _universities

    fun fetchUniversities() {
        viewModelScope.launch {
            try {
                val uni = repository.getUniversities()
                _universities.value = uni
                Log.d("UniversityViewModel", "Fetched universities: $uni")
            } catch (e: Exception) {

            }
        }
    }
}