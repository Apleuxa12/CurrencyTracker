package com.ddmukhin.currencytracker.data.persistence.impl

import com.ddmukhin.currencytracker.converter.CurrenciesConverter
import com.ddmukhin.currencytracker.data.persistence.dao.CurrencyDao
import com.ddmukhin.currencytracker.data.persistence.PersistenceRepository
import com.ddmukhin.currencytracker.ui.model.CurrencyItem

class PersistenceRepositoryImpl(
    private val currencyDao: CurrencyDao,
    private val currenciesConverter: CurrenciesConverter
) : PersistenceRepository {
    override suspend fun getAll(): List<CurrencyItem> {
        return currencyDao.getAll().map { currenciesConverter.databaseToUi(it) }
    }

    override suspend fun insert(vararg currencies: CurrencyItem) {
        return currencyDao.insert(*(currencies.map { currenciesConverter.uiToDatabase(it) }
            .toTypedArray()))
    }

    override suspend fun delete(vararg currencies: CurrencyItem) {
        return currencyDao.delete(*(currencies.map { currenciesConverter.uiToDatabase(it) }
            .toTypedArray()))
    }
}