package com.example.emptyness;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {databasetest.class},version = 3)
public abstract class AzanDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "AzanDB";
    private static AzanDataBase Instance;
    public abstract dao postdao();

    public static AzanDataBase getInstance(final Context context) {
        if(Instance==null){
        Instance = Room.databaseBuilder(context.getApplicationContext(),AzanDataBase.class,DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        }
        return Instance;

    }

}
