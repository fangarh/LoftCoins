package com.dds.loftcoins.utils;

import com.dds.loftcoins.utils.helpers.IImageLoader;
import com.dds.loftcoins.utils.helpers.PicassoImageLoader;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class UtilModule {

    @Binds
    abstract IImageLoader imageLoader(PicassoImageLoader impl);

}
