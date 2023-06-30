package com.example.recipebook.di

import com.example.recipebook.data.recipeApi
import com.example.recipebook.data.recipeRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesApi(): recipeApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.30.7:8080/")
            .build()
            .create(recipeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: recipeApi): recipeRepoImpl {
        return recipeRepoImpl(api)
    }

}