package com.dds.loftcoins.domain.coins;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
abstract class Listing {
    abstract List<AutoValue_CmcCoin> data();
}
