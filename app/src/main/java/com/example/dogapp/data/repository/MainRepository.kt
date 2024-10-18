package com.example.dogapp.data.repository

import android.util.Log
import com.example.dogapp.data.source.Resource
import com.example.dogapp.data.source.local.LocalDataSource
import com.example.dogapp.data.source.local.entity.PetsEntity
import com.example.dogapp.data.source.remote.RemoteDataSource
import com.example.dogapp.data.source.remote.network.ApiResponse
import com.example.dogapp.domain.model.PetsItem
import com.example.dogapp.domain.repository.IMainRepository
import com.example.dogapp.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class MainRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) : IMainRepository {
    override fun getListPet(): Flow<Resource<List<PetsItem>>> = flow{
        emit(Resource.Loading(true))
        when(val apiResponse = remoteDataSource.getListPet().first()) {
            is ApiResponse.Success -> {
                val data = DataMapper.petResponseToPetItem(apiResponse.data)
                Log.d("MainRepository", "Data mapped: $data")
                emit(Resource.Success(data))
            }
            is ApiResponse.Error -> {
                Log.d("MainRepository", "Error: ${apiResponse.message}")
                emit(Resource.Error(apiResponse.message))
            }
            is ApiResponse.Empty -> {
                Log.d("MainRepository", "No data")
            }
        }
    }

    override fun getFavoritePets(): Flow<Resource<List<PetsEntity>>> {
        return localDataSource.getFavoritePets()
    }

    override suspend fun addToFavorite(
        petsEntity: PetsEntity,
        favoriteState: Boolean
    ): Flow<Resource<PetsEntity>> {
        return localDataSource.addToFavorite(petsEntity, favoriteState)
    }

    override suspend fun removeFavorite(
        petsEntity: PetsEntity,
        favoriteState: Boolean
    ): Flow<Resource<PetsEntity>> {
       return localDataSource.removeFavorite(petsEntity, favoriteState)
    }

}