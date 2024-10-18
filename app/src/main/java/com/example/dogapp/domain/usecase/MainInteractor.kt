package com.example.dogapp.domain.usecase

import com.example.dogapp.data.source.Resource
import com.example.dogapp.data.source.local.entity.PetsEntity
import com.example.dogapp.domain.model.PetsItem
import com.example.dogapp.domain.repository.IMainRepository
import kotlinx.coroutines.flow.Flow

class MainInteractor(private val mainRepository: IMainRepository) : MainUseCase {
    override fun getListPet(): Flow<Resource<List<PetsItem>>> = mainRepository.getListPet()

    override fun getFavoritePets(): Flow<Resource<List<PetsEntity>>> = mainRepository.getFavoritePets()

    override suspend fun addToFavorite(
        petsEntity: PetsEntity,
        favoriteState: Boolean
    ): Flow<Resource<PetsEntity>> = mainRepository.addToFavorite(petsEntity, favoriteState)

    override suspend fun removeFavorite(
        petsEntity: PetsEntity,
        favoriteState: Boolean
    ): Flow<Resource<PetsEntity>> = mainRepository.removeFavorite(petsEntity, favoriteState)
}