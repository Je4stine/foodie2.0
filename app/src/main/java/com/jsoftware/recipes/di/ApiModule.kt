package com.jsoftware.recipes.di

import com.jsoftware.recipes.data.remote.mealApi
import com.jsoftware.recipes.data.repository.MealRepoImplementation
import com.jsoftware.recipes.domain.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object ApiModule {
    private const val BASE_URL = "https://www.themealdb.com"

    @Provides
    @Singleton
    fun provideRetrofit(): mealApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(mealApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMealRepo(api:mealApi): MealRepository{
        return MealRepoImplementation(api)
    }


}