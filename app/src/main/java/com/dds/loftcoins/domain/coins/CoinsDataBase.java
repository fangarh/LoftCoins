package com.dds.loftcoins.domain.coins;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {
        RoomCoin.class
}, version = 1)
abstract class CoinsDataBase extends RoomDatabase {
    abstract CoinsDao coins();
}

