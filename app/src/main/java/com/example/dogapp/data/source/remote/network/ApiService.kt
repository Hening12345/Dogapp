package com.example.dogapp.data.source.remote.network

import com.example.dogapp.data.source.remote.response.PetsResponseItem
import retrofit2.http.GET

interface ApiService {
    @GET("breeds")
    suspend fun getListPet() : List<PetsResponseItem>
}