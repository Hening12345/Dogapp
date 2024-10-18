package com.example.dogapp.ui.screen.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogapp.data.source.Resource
import com.example.dogapp.data.source.local.entity.PetsEntity
import com.example.dogapp.domain.model.PetsItem
import com.example.dogapp.domain.usecase.MainUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val mainUseCase: MainUseCase) : ViewModel() {
    private val _list = MutableLiveData<Resource<List<PetsItem>>>()

    val list: LiveData<Resource<List<PetsItem>>> get() = _list

    private val _favorite = MutableLiveData<Resource<List<PetsEntity>>>()

    val favorite: LiveData<Resource<List<PetsEntity>>> get() = _favorite


    fun getListPet() {
        _list.value = Resource.Loading(true)
        viewModelScope.launch {
            mainUseCase.getListPet().collect {
                _list.value = it
                Log.d("MainViewModel", "data: $it")
            }
        }
    }

    fun getFavoritePets() {
        _favorite.value = Resource.Loading(true)
        viewModelScope.launch {
            mainUseCase.getFavoritePets().collect {
                _favorite.value = it
                Log.d("MainViewModel", "data: $it")
            }
        }
    }

    fun addToFavorite(petsEntity: PetsEntity, isFavorite: Boolean) {
        viewModelScope.launch {
            mainUseCase.addToFavorite(petsEntity, isFavorite).collect {
                Log.d("MainViewModel", "data: $it")
            }
        }
    }

    fun removeFavorite(petsEntity: PetsEntity, isFavorite: Boolean) {
        viewModelScope.launch {
            mainUseCase.removeFavorite(petsEntity, isFavorite).collect {
                getFavoritePets()
            }
        }
    }
}