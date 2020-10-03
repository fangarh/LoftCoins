package com.dds.loftcoins.domain.coins;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.auto.value.AutoValue;

import java.util.List;

public interface ICoinsRepository {
    @NonNull
    LiveData<List<ICoin>> listings(@NonNull Query query);

    @AutoValue
    abstract class Query {

        @NonNull
        public static Builder builder() {
            return new AutoValue_ICoinsRepository_Query.Builder()
                    .forceUpdate(true);
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
