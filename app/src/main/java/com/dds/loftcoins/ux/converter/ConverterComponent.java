package com.dds.loftcoins.ux.converter;


import androidx.lifecycle.ViewModelProvider;

import com.dds.loftcoins.BaseComponent;
import com.dds.loftcoins.utils.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ConverterModule.class,
        ViewModelModule.class
}, dependencies = {
        BaseComponent.class
})
abstract class ConverterComponent {

    abstract ViewModelProvider.Factory viewModelFactory();

    abstract CoinsSheetAdapter coinsSheetAdapter();

}