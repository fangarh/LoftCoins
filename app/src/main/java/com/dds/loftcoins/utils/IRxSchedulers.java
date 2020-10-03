package com.dds.loftcoins.utils;

import androidx.annotation.NonNull;

import io.reactivex.Scheduler;

public interface IRxSchedulers {
    @NonNull
    Scheduler io();

    @NonNull
    Scheduler cmp();

    @NonNull
    Scheduler main();
}
