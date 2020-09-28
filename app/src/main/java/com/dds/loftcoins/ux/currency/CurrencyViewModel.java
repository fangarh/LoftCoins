package com.dds.loftcoins.ux.currency;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dds.loftcoins.domain.coins.dtc.Currency;
import com.dds.loftcoins.domain.coins.dtc.ICurrencyRepository;

import java.util.List;

import javax.inject.Inject;

class CurrencyViewModel extends ViewModel {

    private final ICurrencyRepository currencyRepo;

    @Inject
    CurrencyViewModel(ICurrencyRepository currencyRepo) {
        this.currencyRepo = currencyRepo;
    }

    @NonNull
    LiveData<List<Currency>> allCurrencies() {
        return currencyRepo.availableCurrencies();
    }

    void updateCurrency(@NonNull Currency currency) {
        currencyRepo.updateCurrency(currency);
    }

}
