package com.example.dogapp.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dogapp.data.source.local.entity.PetsEntity

@Dao
interface PetsDao {
    @Query("SELECT * FROM pets")
    fun getListPets() : LiveData<List<PetsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPets(pets: PetsEntity)

    @Delete
    suspend fun deletePets(pets: PetsEntity)

    @Query("SELECT * FROM pets where favorite = 1")
    fun getFavoritePets() : LiveData<List<PetsEntity>>
}