package com.dds.loftcoins.domain.coins.dtc;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class CoinsListingDTC {

    abstract List<AutoValue_Coin> data();
}
