package com.dds.loftcoins.ux.currency;

import androidx.lifecycle.ViewModelProvider;

import com.dds.loftcoins.BaseComponent;
import com.dds.loftcoins.utils.view.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        CurrencyModule.class,
        ViewModelModule.class
}, dependencies = {
        BaseComponent.class
})
abstract class CurrencyComponent {

    abstract ViewModelProvider.Factory viewModelFactory();

}
