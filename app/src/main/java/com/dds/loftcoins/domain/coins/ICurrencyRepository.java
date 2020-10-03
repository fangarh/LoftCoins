package com.dds.loftcoins.domain.coins;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Observable;

public interface ICurrencyRepository {
    @NonNull
    LiveData<List<Currency>> availableCurrencies();

    @NonNull
    Observable<Currency> currency();

    void updateCurrency(@NonNull Currency currency);
}
