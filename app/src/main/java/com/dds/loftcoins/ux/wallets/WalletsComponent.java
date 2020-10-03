package com.dds.loftcoins.ux.wallets;

import androidx.lifecycle.ViewModelProvider;

import com.dds.loftcoins.BaseComponent;
import com.dds.loftcoins.utils.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        WalletsModule.class,
        ViewModelModule.class
}, dependencies = {
        BaseComponent.class
})
abstract class WalletsComponent {
    abstract ViewModelProvider.Factory viewModelFactory();

    abstract WalletsAdapter walletsAdapter();

    abstract TransactionsAdapter transactionsAdapter();
}

