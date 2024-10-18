package com.example.dogapp.domain.model

data class Pets(
	val pets: List<PetsItem?>? = null
)

data class Height(
	val metric: String? = null,
	val imperial: String? = null
)

data class Weight(
	val metric: String? = null,
	val imperial: String? = null
)

data class PetsItem(
	val lifeSpan: String? = null,
	val breedGroup: String? = null,
	val temperament: String? = null,
	val name: String? = null,
	val weight: Weight? = null,
	val bredFor: String? = null,
	val id: Int? = null,
	val referenceImageId: String? = null,
	val height: Height? = null,
	val history: String? = null,
	val countryCode: String? = null,
	val description: String? = null,
	val origin: String? = null,
	val favorite: Boolean? = false
)

