package com.dds.loftcoins.domainstub;

import android.content.Context;

import androidx.annotation.NonNull;

import com.dds.loftcoins.domain.coins.CurrencyRepository;

public class CurencyRepositoryStub extends CurrencyRepository {

    public CurencyRepositoryStub(@NonNull Context context) {
        super(context);
    }
}
