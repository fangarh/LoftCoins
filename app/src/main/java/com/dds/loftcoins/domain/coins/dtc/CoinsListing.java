package com.dds.loftcoins.domain.coins.dtc;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class CoinsListing {

    abstract List<AutoValue_CoinDtc> data();
}
