package com.ddmukhin.currencytracker.data.persistence

import com.ddmukhin.currencytracker.data.persistence.model.Currency
import com.ddmukhin.currencytracker.ui.model.CurrencyItem

interface PersistenceRepository {

    suspend fun getAll(): List<CurrencyItem>

    suspend fun insert(vararg currencies: CurrencyItem)
}