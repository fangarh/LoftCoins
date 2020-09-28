package com.dds.loftcoins.domain.coins.dtc;

import androidx.annotation.NonNull;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Query {

    @NonNull
    public static Builder builder() {
     /*   return new AutoValue_.Builder()
                .forceUpdate(true);*/
     return  null;
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
