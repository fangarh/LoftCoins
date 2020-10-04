package com.dds.loftcoins.domainstub;

import androidx.annotation.NonNull;

import com.dds.loftcoins.domain.coins.Currency;
import com.dds.loftcoins.domain.coins.ICoin;
import com.dds.loftcoins.domain.coins.ICoinsRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class CoinsRepositoryStub implements ICoinsRepository {
    public final Subject<List<ICoin>> listings = PublishSubject.create();

    public Query lastListingsQuery;

    @NonNull
    @Override
    public Observable<List<ICoin>> listings(@NonNull Query query) {
        lastListingsQuery = query;
        return listings;
    }

    @NonNull
    @Override
    public Single<ICoin> coin(@NonNull Currency currency, long id) {
        return Single.error(() -> new AssertionError("Stub!"));
    }

    @NonNull
    @Override
    public Single<ICoin> nextPopularCoin(@NonNull Currency currency, List<Integer> ids) {
        return Single.error(() -> new AssertionError("Stub!"));
    }

    @NonNull
    @Override
    public Observable<List<ICoin>> topCoins(@NonNull Currency currency) {
        return Observable.error(() -> new AssertionError("Stub!"));
    }
}
