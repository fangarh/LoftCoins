package com.dds.loftcoins.utils;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class UtilModule {

    @Binds
    abstract IImageLoader imageLoader(PicassoImageLoader impl);

    @Binds
    abstract IRxSchedulers schedulers(RxSchedulers impl);

    @Binds
    abstract INotifier notifier(Notifier impl);

}

