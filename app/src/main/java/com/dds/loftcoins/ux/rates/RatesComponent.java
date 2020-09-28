package com.dds.loftcoins.ux.rates;

import androidx.lifecycle.ViewModelProvider;

import com.dds.loftcoins.BaseComponent;
import com.dds.loftcoins.utils.view.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        RatesModule.class,
        ViewModelModule.class
}, dependencies = {
        BaseComponent.class
})
abstract class RatesComponent {

    abstract ViewModelProvider.Factory viewModelFactory();

    abstract RatesAdapter ratesAdapter();

}
