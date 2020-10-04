package com.dds.loftcoins.utils;

import android.content.Context;

import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.common.truth.Truth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.NumberFormat;
import java.util.Locale;

@RunWith(AndroidJUnit4.class)
public class PriceFormatterTest {
    private PriceFormatter form;
    private Context context;

    @Before
    public void setUp() throws Exception {
        context = ApplicationProvider.getApplicationContext();
        form = new PriceFormatter(context);
    }

    @Test
    public void format_EUR() {
        final NumberFormat de = NumberFormat.getCurrencyInstance(Locale.GERMANY);

        Truth.assertThat(form.format("EUR", 1.23)).isEqualTo(de.format(1.23));
    }

    @Test
    public void format_RUB() {
        final NumberFormat ru = NumberFormat.getCurrencyInstance(new Locale("ru", "RU"));

        Truth.assertThat(form.format("RUB", 1.23)).isEqualTo(ru.format(1.23));
    }

    @Test
    public void default_context_format_test(){
        final LocaleListCompat locales = ConfigurationCompat
                .getLocales(context.getResources().getConfiguration());

        Truth.assertThat(form.format("CAD", 1.23)).isEqualTo(NumberFormat
                .getCurrencyInstance(locales.get(0)).format(1.23));
    }


    @After
    public void tearDown() throws Exception {

    }
}