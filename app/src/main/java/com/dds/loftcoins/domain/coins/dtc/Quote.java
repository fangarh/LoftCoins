package com.dds.loftcoins.domain.coins.dtc;


import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;

@AutoValue
public abstract class Quote {

    public abstract double price();

    @Json(name = "percent_change_24h")
    @AutoValue.CopyAnnotations
    public abstract double change24h();
}
