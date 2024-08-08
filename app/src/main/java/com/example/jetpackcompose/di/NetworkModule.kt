package com.example.jetpackcompose.di

import android.content.Context
import com.example.jetpackcompose.room_db.PizzaDao
import com.example.jetpackcompose.room_db.PizzaDatabase
import com.example.jetpackcompose.view_models.MainViewModel
import com.example.jetpackcompose.view_models.PizzaViewModel
import com.example.jetpackcompose.view_models.ProfileViewModel
import com.example.jetpackcompose.view_models.SharedViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hreactive.teckhubsolutions.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMainViewModel(apiService: ApiService): MainViewModel{
        return MainViewModel(apiService)
    }

    @Singleton
    @Provides
    fun provideSharedViewModel(): SharedViewModel {
        return SharedViewModel()
    }

    @Singleton
    @Provides
    fun provideProfileViewModel(): ProfileViewModel {
        return ProfileViewModel()
    }

    @Singleton
    @Provides
    fun providePizzaDao(@ApplicationContext context: Context): PizzaDao {
        return PizzaDatabase.getDatabase(context).pizzaDao()
    }

    @Singleton
    @Provides
    fun providePizzaViewModel(pizzaDao: PizzaDao): PizzaViewModel {
        return PizzaViewModel(pizzaDao)
    }
}