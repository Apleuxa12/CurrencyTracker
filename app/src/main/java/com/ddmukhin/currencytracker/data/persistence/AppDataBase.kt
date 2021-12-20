package com.ddmukhin.currencytracker.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ddmukhin.currencytracker.data.persistence.model.Currency

@Database(entities = [Currency::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

}