package com.example.dogapp.di

import android.content.Context
import androidx.room.Room
import com.example.dogapp.data.repository.MainRepository
import com.example.dogapp.data.source.local.LocalDataSource
import com.example.dogapp.data.source.local.room.PetsDao
import com.example.dogapp.data.source.local.room.PetsDatabase
import com.example.dogapp.data.source.remote.RemoteDataSource
import com.example.dogapp.data.source.remote.network.ApiService
import com.example.dogapp.domain.repository.IMainRepository
import com.example.dogapp.domain.usecase.MainInteractor
import com.example.dogapp.domain.usecase.MainUseCase
import com.example.dogapp.ui.screen.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    factory<MainUseCase> { MainInteractor(get()) }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.thedogapi.com/v1/ ")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

fun provideRoomDatabase(context: Context) : PetsDatabase {
    return Room.databaseBuilder(
        context,
        PetsDatabase::class.java,
        "Pets.db"
    ).build()
}

fun providePetsDao(petsDatabase: PetsDatabase) : PetsDao {
    return petsDatabase.petsDao()
}

val databaseModule = module {
    single { provideRoomDatabase(androidContext()) }
    single { providePetsDao(get()) }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    single<IMainRepository> { MainRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

