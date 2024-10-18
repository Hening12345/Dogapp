package com.example.dogapp.data.source.remote

import android.util.Log
import com.example.dogapp.data.source.remote.network.ApiResponse
import com.example.dogapp.data.source.remote.network.ApiService
import com.example.dogapp.data.source.remote.response.PetsResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getListPet() : Flow<ApiResponse<List<PetsResponseItem>>> = flow {
        try {
            val response = apiService.getListPet()
            Log.d("ListPet", "data: $response")
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            Log.d("ListPet", "error: ${e.message.toString()}")
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}