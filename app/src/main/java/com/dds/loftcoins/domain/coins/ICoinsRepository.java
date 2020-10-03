package com.dds.loftcoins.domain.coins;

import androidx.annotation.NonNull;

import com.google.auto.value.AutoValue;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface ICoinsRepository {
    @NonNull
    Observable<List<ICoin>> listings(@NonNull Query query);

    @NonNull
    Single<ICoin> coin(@NonNull Currency currency, long id);

    @NonNull
    Single<ICoin> nextPopularCoin(@NonNull Currency currency, List<Integer> ids);

    @NonNull
    Observable<List<ICoin>> topCoins(@NonNull Currency currency);

    @AutoValue
    abstract class Query {

        @NonNull
        public static Builder builder() {
            return new AutoValue_ICoinsRepository_Query.Builder()
                    .forceUpdate(true)
                    .sortBy(SortBy.RANK);
        }

        abstract String currency();

        abstract boolean forceUpdate();

        abstract SortBy sortBy();

        @AutoValue.Builder
        public abstract static class Builder {
            public abstract Builder currency(String currency);

            public abstract Builder forceUpdate(boolean forceUpdate);

            public abstract Builder sortBy(SortBy sortBy);

            public abstract Query build();
        }
    }
}
