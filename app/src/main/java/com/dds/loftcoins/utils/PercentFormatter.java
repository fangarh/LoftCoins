package com.dds.loftcoins.utils;

import androidx.annotation.NonNull;

import com.dds.loftcoins.utils.helpers.Formatter;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PercentFormatter implements Formatter<Double> {
    @Inject
    PercentFormatter() {
    }

    @NonNull
    @Override
    public String format(@NonNull Double value) {
        return String.format(Locale.US, "%.2f%%", value);
    }
}
