package com.example.dogapp.domain.repository

import com.example.dogapp.data.source.Resource
import com.example.dogapp.data.source.local.entity.PetsEntity
import com.example.dogapp.domain.model.PetsItem
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
    fun getListPet() : Flow<Resource<List<PetsItem>>>

    fun getFavoritePets() : Flow<Resource<List<PetsEntity>>>

    suspend fun addToFavorite(petsEntity: PetsEntity, favoriteState: Boolean) : Flow<Resource<PetsEntity>>

    suspend fun removeFavorite(petsEntity: PetsEntity, favoriteState: Boolean) : Flow<Resource<PetsEntity>>
}