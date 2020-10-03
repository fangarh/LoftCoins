package com.dds.loftcoins.domain.coins;

import androidx.annotation.NonNull;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Wallet {
    @NonNull
    static Wallet create(String id, ICoin coin, Double balance) {
        return new AutoValue_Wallet(id, coin, balance);
    }

    public abstract String uid();

    public abstract ICoin coin();

    public abstract double balance();
}
