package com.example.dogapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PetsResponse(

	@field:SerializedName("PetsResponse")
	val petsResponse: List<PetsResponseItem?>? = null
)

data class Weight(

	@field:SerializedName("metric")
	val metric: String? = null,

	@field:SerializedName("imperial")
	val imperial: String? = null
)

data class PetsResponseItem(

	@field:SerializedName("life_span")
	val lifeSpan: String? = null,

	@field:SerializedName("breed_group")
	val breedGroup: String? = null,

	@field:SerializedName("temperament")
	val temperament: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("weight")
	val weight: Weight? = null,

	@field:SerializedName("bred_for")
	val bredFor: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("reference_image_id")
	val referenceImageId: String? = null,

	@field:SerializedName("height")
	val height: Height? = null,

	@field:SerializedName("history")
	val history: String? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("origin")
	val origin: String? = null
)

data class Height(

	@field:SerializedName("metric")
	val metric: String? = null,

	@field:SerializedName("imperial")
	val imperial: String? = null
)
