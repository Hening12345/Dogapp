package com.example.dogapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
data class PetsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "bredFor")
    val bredFor: String,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "favorite")
    var isFavorite: Boolean? = null
)