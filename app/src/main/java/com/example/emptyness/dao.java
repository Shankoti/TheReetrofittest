package com.example.emptyness;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface dao {
    @Insert(onConflict = REPLACE)
    void insert(databasetest databasetest);

    @Query("select * from DATASET")
    List<databasetest> getdatatest();
}
