package com.dds.loftcoins;

import android.content.Context;

import com.dds.loftcoins.domain.coins.dtc.ICoinsRepository;
import com.dds.loftcoins.domain.coins.dtc.ICurrencyRepository;
import com.dds.loftcoins.utils.helpers.IImageLoader;

public interface BaseComponent {
    Context context();
    ICoinsRepository coinsRepo();
    ICurrencyRepository currencyRepo();
    IImageLoader imageLoader();
}
