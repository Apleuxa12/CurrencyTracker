package com.ddmukhin.currencytracker.data.persistence.dao

import androidx.room.*
import com.ddmukhin.currencytracker.data.persistence.model.Currency

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency")
    suspend fun getAll(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg currencies: Currency)

    @Delete
    suspend fun delete(vararg currencies: Currency)
}