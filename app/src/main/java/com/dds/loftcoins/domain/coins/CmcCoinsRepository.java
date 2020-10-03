package com.dds.loftcoins.domain.coins;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import retrofit2.Response;
import timber.log.Timber;

@Singleton
class CmcCoinsRepository implements ICoinsRepository{
    private final ICmcApi api;

    private final CoinsDataBase db;

    private final ExecutorService executor;

    @Inject
    public CmcCoinsRepository(ICmcApi api, CoinsDataBase db, ExecutorService executor) {
        this.api = api;
        this.db = db;
        this.executor = executor;
    }

    @NonNull
    @Override
    public LiveData<List<ICoin>> listings(@NonNull Query query) {
        fetchFromNetworkIfNecessary(query);
        return fetchFromDb(query);
    }

    private LiveData<List<ICoin>> fetchFromDb(Query query) {
        LiveData<List<RoomCoin>> coins;
        if (query.sortBy() == SortBy.PRICE) {
            coins = db.coins().fetchAllSortByPrice();
        } else {
            coins = db.coins().fetchAllSortByRank();
        }
        return Transformations.map(coins, ArrayList::new);
    }

    private void fetchFromNetworkIfNecessary(Query query) {
        executor.submit(() -> {
            if (query.forceUpdate() || db.coins().coinsCount() == 0) {
                try {
                    final Response<Listing> response = api.listing(query.currency()).execute();
                    if (response.isSuccessful()) {
                        final Listing listings = response.body();
                        if (listings != null) {
                            saveCoinsIntoDb(query, listings.data());
                        }
                    } else {
                        final ResponseBody responseBody = response.errorBody();
                        if (responseBody != null) {
                            throw new IOException(responseBody.string());
                        }
                    }
                } catch (IOException e) {
                    Timber.e(e);
                }
            }
        });
    }

    private void saveCoinsIntoDb(Query query, List<? extends ICoin> coins) {
        List<RoomCoin> roomCoins = new ArrayList<>(coins.size());
        for (ICoin coin : coins) {
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
        db.coins().insert(roomCoins);
    }
}
