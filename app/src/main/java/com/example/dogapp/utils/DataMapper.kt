package com.example.dogapp.utils


import com.example.dogapp.data.source.local.entity.PetsEntity
import com.example.dogapp.data.source.remote.response.PetsResponseItem
import com.example.dogapp.domain.model.PetsItem

object DataMapper {
    fun petResponseToPetItem(data: List<PetsResponseItem>) : List<PetsItem> =
        data.filter { it.bredFor?.isNotBlank() == true }.map { responseItem ->
        PetsItem(
                name = responseItem.name,
                lifeSpan = responseItem.lifeSpan,
                breedGroup = responseItem.temperament,
                id = responseItem.id,
                bredFor = responseItem.bredFor,
                referenceImageId = responseItem.referenceImageId,
                history = responseItem.history,
                countryCode = responseItem.countryCode,
                description = responseItem.description,
                origin = responseItem.origin,
            )
        }

    fun mapToPetsEntity(pet: PetsItem, isFavorite: Boolean): PetsEntity {
        return PetsEntity(
            name = pet.name!!,
            bredFor = pet.bredFor!!,
            image = pet.referenceImageId,
            isFavorite = isFavorite
        )
    }
}