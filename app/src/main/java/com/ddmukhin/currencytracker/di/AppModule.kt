package com.ddmukhin.currencytracker.di

import com.ddmukhin.currencytracker.converter.CurrenciesConverter
import com.ddmukhin.currencytracker.data.network.CurrencyRepository
import com.ddmukhin.currencytracker.data.network.CurrencyRepositoryImpl
import com.ddmukhin.currencytracker.data.network.CurrencyService
import com.ddmukhin.currencytracker.data.persistence.CurrencyDao
import com.ddmukhin.currencytracker.data.persistence.PersistenceRepository
import com.ddmukhin.currencytracker.data.persistence.PersistenceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun provideCurrencyRepository(
        currencyService: CurrencyService,
        currenciesConverter: CurrenciesConverter
    ): CurrencyRepository = CurrencyRepositoryImpl(currencyService, currenciesConverter)

    @Provides
    fun providePersistenceRepository(
        currencyDao: CurrencyDao,
        currenciesConverter: CurrenciesConverter
    ): PersistenceRepository = PersistenceRepositoryImpl(currencyDao, currenciesConverter)

}