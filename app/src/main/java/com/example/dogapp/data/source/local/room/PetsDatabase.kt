package com.example.dogapp.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dogapp.data.source.local.entity.PetsEntity

@Database(entities = [PetsEntity::class], version = 1, exportSchema = false)
abstract class PetsDatabase : RoomDatabase() {
    abstract fun petsDao() : PetsDao
}