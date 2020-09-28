package com.dds.loftcoins.domain.coins.dtc;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {
        RoomCoin.class
}, version = 1)
abstract class DatabaseAdapter extends RoomDatabase {
    abstract CoinsDao coins();
}
