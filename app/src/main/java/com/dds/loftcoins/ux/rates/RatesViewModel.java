package com.dds.loftcoins.ux.rates;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.dds.loftcoins.domain.coins.dtc.CmcCoinsRepository;
import com.dds.loftcoins.domain.coins.dtc.ICoin;
import com.dds.loftcoins.domain.coins.dtc.ICurrencyRepository;
import com.dds.loftcoins.domain.coins.dtc.Query;
import com.dds.loftcoins.domain.coins.dtc.SortBy;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;

public class RatesViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();

    private final MutableLiveData<AtomicBoolean> forceRefresh = new MutableLiveData<>(new AtomicBoolean(true));

    private final MutableLiveData<SortBy> sortBy = new MutableLiveData<>(SortBy.RANK);

    private final LiveData<List<ICoin>> coins;

    private int sortingIndex = 1;

    // AppComponent(BaseComponent) -> MainComponent -> Fragment(BaseComponent) -> RatesComponent -> RatesViewModel()

    @Inject
    public RatesViewModel(CmcCoinsRepository coinsRepo, ICurrencyRepository currencyRepo) {
        //    t           f            f          f         f          t
        // (f|t) -> forceRefresh -> currency -> sortBy -> query -> listings
        // USD -> RUB -> USD -> USD -> USD -> EUR -> EUR
        // Transformations.distinctUntilChanged()
        final LiveData<Query> query = Transformations.switchMap(forceRefresh, (r) -> {
            return Transformations.switchMap(currencyRepo.currency(), (c) -> {
                r.set(true);
                isRefreshing.postValue(true);
                return Transformations.map(sortBy, (s) -> {
                    return Query.builder()
                            .currency(c.code())
                            .forceUpdate(r.getAndSet(false))
                            .sortBy(s)
                            .build();
                });
            });
        });
        final LiveData<List<ICoin>> coins = Transformations.switchMap(query, coinsRepo::GetCoinsRates);
        this.coins = Transformations.map(coins, (c) -> {
            isRefreshing.postValue(false);
            return c;
        });
    }

    @NonNull
    LiveData<List<ICoin>> coins() {
        return coins;
    }

    @NonNull
    LiveData<Boolean> isRefreshing() {
        return isRefreshing;
    }

    final void refresh() {
        forceRefresh.postValue(new AtomicBoolean(true));
    }

    void switchSortingOrder() {
        sortBy.postValue(SortBy.values()[sortingIndex++ % SortBy.values().length]);
    }


}
