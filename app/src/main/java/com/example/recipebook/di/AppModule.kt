package com.example.recipebook.di

import com.example.recipebook.data.recipeApi
import com.example.recipebook.data.recipeRepo
import com.example.recipebook.data.recipeRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesApi(): recipeApi {
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRepository(api: recipeApi): recipeRepoImpl {
        return recipeRepoImpl(api)
    }

}