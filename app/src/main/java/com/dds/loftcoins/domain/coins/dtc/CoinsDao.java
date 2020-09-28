package com.dds.loftcoins.domain.coins.dtc;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
abstract class CoinsDao {

    @androidx.room.Query("SELECT * FROM RoomCoin")
    abstract LiveData<List<RoomCoin>> fetchAll();

    @androidx.room.Query("SELECT * FROM RoomCoin ORDER BY price DESC")
    abstract LiveData<List<RoomCoin>> fetchAllSortByPrice();

    @androidx.room.Query("SELECT * FROM RoomCoin ORDER BY rank ASC")
    abstract LiveData<List<RoomCoin>> fetchAllSortByRank();

    @WorkerThread
    @Query("SELECT COUNT(id) FROM RoomCoin")
    abstract int coinsCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insert(List<RoomCoin> coins);

}
