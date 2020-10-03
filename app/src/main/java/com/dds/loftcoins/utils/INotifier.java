package com.dds.loftcoins.utils;

import androidx.annotation.NonNull;

import io.reactivex.Completable;

public interface INotifier {
    @NonNull
    Completable sendMessage(@NonNull String title, @NonNull String message, @NonNull Class<?> receiver);
}
