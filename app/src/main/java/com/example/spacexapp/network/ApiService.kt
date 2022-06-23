package com.example.spacexapp.network

import com.example.spacexapp.utils.Constants
import com.example.spacexapp.models.Rocket
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.ROCKET_URL)
    suspend fun getRockets(): Response<List<Rocket>>
}