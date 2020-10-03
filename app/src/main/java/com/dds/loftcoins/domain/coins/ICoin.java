package com.dds.loftcoins.domain.coins;

public interface ICoin {
    int id();

    String name();

    String symbol();

    int rank();

    double price();

    double change24h();

    String currencyCode();
}
