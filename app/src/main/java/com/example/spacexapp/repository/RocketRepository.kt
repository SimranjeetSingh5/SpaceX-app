package com.example.spacexapp.repository

import androidx.lifecycle.LiveData
import com.example.spacexapp.models.Rocket
import com.example.spacexapp.network.ApiService
import javax.inject.Inject

class RocketRepository @Inject constructor(private val apiService: ApiService
//,private val rocketDao: RocketDao
) {

    suspend fun getRockets() = apiService.getRockets()


}