package com.dds.loftcoins;

import android.content.Context;

import com.dds.loftcoins.domain.coins.ICoinsRepository;
import com.dds.loftcoins.domain.coins.ICurrencyRepository;
import com.dds.loftcoins.utils.IImageLoader;
import com.dds.loftcoins.utils.INotifier;
import com.dds.loftcoins.utils.IRxSchedulers;

public interface BaseComponent {
    Context context();
    ICoinsRepository coinsRepo();
    ICurrencyRepository currencyRepo();
    IImageLoader imageLoader();
    IRxSchedulers schedulers();

    INotifier notifier();
}
