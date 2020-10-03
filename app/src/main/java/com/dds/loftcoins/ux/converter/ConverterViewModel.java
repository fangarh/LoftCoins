package com.dds.loftcoins.ux.converter;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.dds.loftcoins.domain.coins.ICoin;
import com.dds.loftcoins.domain.coins.ICoinsRepository;
import com.dds.loftcoins.domain.coins.ICurrencyRepository;
import com.dds.loftcoins.utils.IRxSchedulers;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

class ConverterViewModel extends ViewModel {
    private final Subject<ICoin> fromCoin = BehaviorSubject.create();

    private final Subject<ICoin> toCoin = BehaviorSubject.create();

    private final Subject<String> fromValue = BehaviorSubject.create();

    private final Subject<String> toValue = BehaviorSubject.create();

    private final Observable<List<ICoin>> topCoins;

    private final Observable<Double> factor;

    private final IRxSchedulers schedulers;

    @Inject
    ConverterViewModel(ICoinsRepository coinsRepo, ICurrencyRepository currencyRepo, IRxSchedulers schedulers) {
        this.schedulers = schedulers;

        topCoins = currencyRepo.currency()
                .switchMap(coinsRepo::topCoins)
                .doOnNext((coins) -> fromCoin.onNext(coins.get(0)))
                .doOnNext((coins) -> toCoin.onNext(coins.get(1)))
                .replay(1)
                .autoConnect();

        factor = fromCoin
                .flatMap((fc) -> toCoin
                        .map((tc) -> fc.price() / tc.price())
                )
                .replay(1)
                .autoConnect();
    }

    @NonNull
    Observable<List<ICoin>> topCoins() {
        return topCoins.observeOn(schedulers.main());
    }

    @NonNull
    Observable<ICoin> fromCoin() {
        return fromCoin.observeOn(schedulers.main());
    }

    @NonNull
    Observable<ICoin> toCoin() {
        return toCoin.observeOn(schedulers.main());
    }

    @NonNull
    Observable<String> fromValue() {
        return fromValue.observeOn(schedulers.main());
    }

    @NonNull
    Observable<String> toValue() {
        return fromValue
                .observeOn(schedulers.cmp())
                .map((s) -> s.isEmpty() ? "0.0" : s)
                .map(Double::parseDouble)
                .flatMap((value) -> factor.map((f) -> value * f))
                .map(v -> String.format(Locale.US, "%.2f", v))
                .map((v) -> "0.0".equals(v) ? "" : v)
                .observeOn(schedulers.main());
    }

    void fromCoin(ICoin coin) {
        fromCoin.onNext(coin);
    }

    void toCoin(ICoin coin) {
        toCoin.onNext(coin);
    }

    void fromValue(CharSequence text) {
        fromValue.onNext(text.toString());
    }

    void toValue(CharSequence text) {
        toValue.onNext(text.toString());
    }
}