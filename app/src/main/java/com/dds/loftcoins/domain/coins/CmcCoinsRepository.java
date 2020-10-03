package com.dds.loftcoins.domain.coins;

import androidx.annotation.NonNull;

import com.dds.loftcoins.utils.IRxSchedulers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class CmcCoinsRepository implements ICoinsRepository{
    private final ICmcApi api;

    private final CoinsDataBase db;

    private final IRxSchedulers schedulers;

    @Inject
    public CmcCoinsRepository(ICmcApi api, CoinsDataBase db, IRxSchedulers schedulers) {
        this.api = api;
        this.db = db;
        this.schedulers = schedulers;
    }

    @NonNull
    @Override
    public Observable<List<ICoin>> listings(@NonNull Query query) {
        return Observable
                .fromCallable(() -> query.forceUpdate() || db.coins().coinsCount() == 0)
                .switchMap((f) -> f ? api.listing(query.currency()) : Observable.empty())
                .map((listings) -> mapToRoomCoins(query, listings.data()))
                .doOnNext((coins) -> db.coins().insert(coins))
                .switchMap((coins) -> fetchFromDb(query))
                .switchIfEmpty(fetchFromDb(query))
                .<List<ICoin>>map(Collections::unmodifiableList)
                .subscribeOn(schedulers.io());
    }

    @NonNull
    @Override
    public Single<ICoin> coin(@NonNull Currency currency, long id) {
        return listings(Query.builder().currency(currency.code()).forceUpdate(false).build())
                .switchMapSingle((coins) -> db.coins().fetchOne(id))
                .firstOrError()
                .map((coin) -> coin);
    }

    @NonNull
    @Override
    public Single<ICoin> nextPopularCoin(@NonNull Currency currency, List<Integer> ids) {
        return listings(Query.builder().currency(currency.code()).forceUpdate(false).build())
                .switchMapSingle((coins) -> db.coins().nextPopularCoin(ids))
                .firstOrError()
                .map((coin) -> coin);
    }

    @NonNull
    @Override
    public Observable<List<ICoin>> topCoins(@NonNull Currency currency) {
        return listings(Query.builder().currency(currency.code()).forceUpdate(false).build())
                .switchMap((coins) -> db.coins().fetchTop(3))
                .<List<ICoin>>map(Collections::unmodifiableList);
    }

    private Observable<List<RoomCoin>> fetchFromDb(Query query) {
        if (query.sortBy() == SortBy.PRICE) {
            return db.coins().fetchAllSortByPrice();
        } else {
            return db.coins().fetchAllSortByRank();
        }
    }

    private List<RoomCoin> mapToRoomCoins(Query query, List<? extends ICoin> data) {
        List<RoomCoin> roomCoins = new ArrayList<>(data.size());
        for (ICoin coin : data) {
            roomCoins.add(RoomCoin.create(
                    coin.name(),
                    coin.symbol(),
                    coin.rank(),
                    coin.price(),
                    coin.change24h(),
                    query.currency(),
                    coin.id()
            ));
        }
        return roomCoins;
    }
}
