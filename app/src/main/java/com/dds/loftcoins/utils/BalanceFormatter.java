package com.dds.loftcoins.utils;

import androidx.annotation.NonNull;

import com.dds.loftcoins.domain.coins.Wallet;
import com.dds.loftcoins.utils.helpers.Formatter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BalanceFormatter implements Formatter<Wallet> {
    @Inject
    BalanceFormatter() {
    }

    @NonNull
    @Override
    public String format(@NonNull Wallet value) {
        final DecimalFormat format = (DecimalFormat) NumberFormat.getCurrencyInstance();
        final DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
        symbols.setCurrencySymbol(value.coin().symbol());
        format.setDecimalFormatSymbols(symbols);
        return format.format(value.balance());
    }
}
