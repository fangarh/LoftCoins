package com.dds.loftcoins.utils;

import androidx.annotation.NonNull;

import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class RxSchedulers implements IRxSchedulers {
    private final Scheduler ioScheduler;

    @Inject
    RxSchedulers(ExecutorService executor) {
        ioScheduler = Schedulers.from(executor);
    }

    @NonNull
    @Override
    public Scheduler io() {
        return ioScheduler;
    }

    @NonNull
    @Override
    public Scheduler cmp() {
        return Schedulers.computation();
    }

    @NonNull
    @Override
    public Scheduler main() {
        return AndroidSchedulers.mainThread();
    }
}
