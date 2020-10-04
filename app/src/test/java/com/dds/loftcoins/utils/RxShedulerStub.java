package com.dds.loftcoins.utils;

import androidx.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class RxShedulerStub implements IRxSchedulers {
    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler cmp() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler main() {
        return Schedulers.trampoline();
    }
}
