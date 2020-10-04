package com.dds.loftcoins.utils;

import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;

public class PercentFormatterTest {
    private PercentFormatter formater;

    @Before
    public void setUp() throws Exception {
        formater = new PercentFormatter();
    }

    @Test
    public void format_test() {
        Truth.assertThat(formater.format(1d)).isEqualTo("1.00%");
        Truth.assertThat(formater.format(1.23456)).isEqualTo("1.23%");
        Truth.assertThat(formater.format(1.2367)).isEqualTo("1.24%");
    }
}