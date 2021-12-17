package com.ddmukhin.currencytracker.di

import com.ddmukhin.currencytracker.converter.CurrenciesConverter
import com.ddmukhin.currencytracker.data.network.CurrencyRepository
import com.ddmukhin.currencytracker.data.network.CurrencyRepositoryImpl
import com.ddmukhin.currencytracker.data.network.CurrencyService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    fun provideCurrencyRepository(
        currencyService: CurrencyService,
        currenciesConverter: CurrenciesConverter
    ): CurrencyRepository = CurrencyRepositoryImpl(currencyService, currenciesConverter)

}