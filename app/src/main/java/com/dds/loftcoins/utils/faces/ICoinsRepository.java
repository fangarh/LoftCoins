package com.dds.loftcoins.utils.faces;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import com.dds.loftcoins.domain.coins.dtc.Coin;

import java.io.IOException;
import java.util.List;

public interface ICoinsRepository {
    @NonNull
    @WorkerThread
    List<? extends Coin> GetCoinsRates(@NonNull String curency) throws IOException;
}
