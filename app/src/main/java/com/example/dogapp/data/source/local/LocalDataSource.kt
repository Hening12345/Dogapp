package com.example.dogapp.data.source.local

import android.util.Log
import androidx.lifecycle.asFlow
import com.example.dogapp.data.source.Resource
import com.example.dogapp.data.source.local.entity.PetsEntity
import com.example.dogapp.data.source.local.room.PetsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(private val petsDao: PetsDao) {

    fun getFavoritePets() : Flow<Resource<List<PetsEntity>>> = flow{
        emit(Resource.Loading(true))
        try {
            val pets = petsDao.getFavoritePets().asFlow().first()
            emit(Resource.Success(pets))
        } catch (e: Exception) {
            Log.d("LocalDataSource", "Error retrieving favorite pets: ${e.localizedMessage}")
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun addToFavorite(pet: PetsEntity, favoriteState: Boolean) : Flow<Resource<PetsEntity>> = flow {
        try {
            pet.isFavorite = favoriteState
            petsDao.insertPets(pet)
            emit(Resource.Success(pet))
        }catch (e: Exception) {
            Log.d("LocalDataSource", "error : $pet")
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun removeFavorite(pet: PetsEntity, favoriteState: Boolean) : Flow<Resource<PetsEntity>> = flow {
        try {
            pet.isFavorite = favoriteState
            petsDao.deletePets(pet)
            emit(Resource.Success(pet))
        }catch (e: Exception) {
            Log.d("LocalDataSource", "error : $pet")
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}