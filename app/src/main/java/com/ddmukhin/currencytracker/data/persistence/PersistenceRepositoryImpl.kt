package com.ddmukhin.currencytracker.data.persistence

import com.ddmukhin.currencytracker.converter.CurrenciesConverter
import com.ddmukhin.currencytracker.data.persistence.model.Currency
import com.ddmukhin.currencytracker.ui.model.CurrencyItem

class PersistenceRepositoryImpl(
    private val currencyDao: CurrencyDao,
    private val currenciesConverter: CurrenciesConverter
) : PersistenceRepository {
    override suspend fun getAll(): List<Currency> {
        return currencyDao.getAll()
    }

    override suspend fun insert(vararg currencies: CurrencyItem) {
        return currencyDao.insert(*(currencies.map { currenciesConverter.uiToDatabase(it) }.toTypedArray()))
    }
}