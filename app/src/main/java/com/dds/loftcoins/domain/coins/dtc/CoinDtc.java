package com.dds.loftcoins.domain.coins.dtc;

import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.auto.value.extension.memoized.Memoized;
import com.squareup.moshi.Json;

import java.util.Iterator;
import java.util.Map;

@AutoValue
public abstract class CoinDtc implements ICoin {
    @Memoized
    @Override
    public double price() {
        final Iterator<? extends Quote> iterator = quote().values().iterator();
        if (iterator.hasNext()) return iterator.next().price();
        return 0d;
    }

    @Memoized
    @Override
    public double change24h() {
        final Iterator<? extends Quote> iterator = quote().values().iterator();
        if (iterator.hasNext()) return iterator.next().change24h();
        return 0d;
    }

    @Json(name = "cmc_rank")
    @AutoValue.CopyAnnotations
    public abstract int rank();

    @Override
    @Nullable
    public abstract String currencyCode();

    public abstract Map<String, AutoValue_Quote> quote();

}
