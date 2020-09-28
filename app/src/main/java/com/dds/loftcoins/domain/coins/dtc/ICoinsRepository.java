package com.dds.loftcoins.domain.coins.dtc;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.io.IOException;
import java.util.List;

public interface ICoinsRepository {
    @NonNull
    LiveData<List<ICoin>> GetCoinsRates(@NonNull Query query) throws IOException;
}
