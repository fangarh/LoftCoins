package com.dds.loftcoins.domain.coins;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public interface ICurrencyRepository {
    @NonNull
    LiveData<List<Currency>> availableCurrencies();

    @NonNull
    LiveData<Currency> currency();

    void updateCurrency(@NonNull Currency currency);
}
