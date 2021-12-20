package com.ddmukhin.currencytracker.di

import com.ddmukhin.currencytracker.converter.CurrenciesConverter
import com.ddmukhin.currencytracker.converter.ErrorConverter
import com.ddmukhin.currencytracker.data.remote.CurrencyRepository
import com.ddmukhin.currencytracker.data.remote.impl.CurrencyRepositoryImpl
import com.ddmukhin.currencytracker.data.remote.CurrencyService
import com.ddmukhin.currencytracker.data.persistence.dao.CurrencyDao
import com.ddmukhin.currencytracker.data.persistence.PersistenceRepository
import com.ddmukhin.currencytracker.data.persistence.impl.PersistenceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    @ViewModelScoped
    fun provideCurrencyRepository(
        currencyService: CurrencyService,
        currenciesConverter: CurrenciesConverter,
        errorConverter: ErrorConverter
    ): CurrencyRepository = CurrencyRepositoryImpl(currencyService, currenciesConverter, errorConverter)

    @Provides
    @ViewModelScoped
    fun providePersistenceRepository(
        currencyDao: CurrencyDao,
        currenciesConverter: CurrenciesConverter
    ): PersistenceRepository = PersistenceRepositoryImpl(currencyDao, currenciesConverter)

}