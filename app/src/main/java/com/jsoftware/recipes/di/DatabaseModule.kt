package com.jsoftware.recipes.di

import androidx.room.Room
import com.jsoftware.recipes.MyApplication
import com.jsoftware.recipes.data.local.MealDao
import com.jsoftware.recipes.data.local.MealDatabase
import com.jsoftware.recipes.data.repository.MealDbRepoImpl
import com.jsoftware.recipes.domain.repository.MealDbRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: MyApplication
    ): MealDatabase{
        return Room.databaseBuilder(
            application,
            MealDatabase::class.java,
            "mealdb.db"
        ).build()

    }

    @Provides
    @Singleton
    fun provideMealDao(database: MealDatabase) : MealDao{
        return database.mealDao()
    }

    @Provides
    @Singleton
    fun provideMealRepo(mealDao: MealDao) :MealDbRepo{
        return MealDbRepoImpl(mealDao)
    }
}