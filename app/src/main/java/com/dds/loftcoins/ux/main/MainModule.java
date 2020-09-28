package com.dds.loftcoins.ux.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.dds.loftcoins.utils.AppFragmentFactory;
import com.dds.loftcoins.ux.converter.ConverterFragment;
import com.dds.loftcoins.ux.currency.CurrencyDialog;
import com.dds.loftcoins.ux.rates.RatesFragment;
import com.dds.loftcoins.ux.wallets.WalletsFragment;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
abstract class MainModule {

    @Binds
    abstract FragmentFactory fragmentFactory(AppFragmentFactory impl);

    @Binds
    @IntoMap
    @ClassKey(RatesFragment.class)
    abstract Fragment ratesFragment(RatesFragment impl);

    @Binds
    @IntoMap
    @ClassKey(WalletsFragment.class)
    abstract Fragment walletsFragment(WalletsFragment impl);

    @Binds
    @IntoMap
    @ClassKey(ConverterFragment.class)
    abstract Fragment converterFragment(ConverterFragment impl);

    @Binds
    @IntoMap
    @ClassKey(CurrencyDialog.class)
    abstract Fragment currencyDialog(CurrencyDialog impl);

}

