package com.ddmukhin.currencytracker.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ddmukhin.currencytracker.data.persistence.model.Currency

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency")
    suspend fun getAll(): List<Currency>

    @Insert
    suspend fun insert(vararg currencies: Currency)
}