package com.ddmukhin.currencytracker.di

import android.content.Context
import androidx.room.Room
import com.ddmukhin.currencytracker.data.persistence.AppDataBase
import com.ddmukhin.currencytracker.data.persistence.CurrencyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    companion object {
        const val DB_NAME = "currency_tracker_db"
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): AppDataBase =
        Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            DB_NAME
        ).build()

    @Provides
    fun provideCurrencyDao(database: AppDataBase): CurrencyDao = database.currencyDao()
}