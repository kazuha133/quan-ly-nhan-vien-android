package com.example.bai2.utils;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.bai2.dao.DatPhongDao;
import com.example.bai2.model.DatPhong;

@Database(entities = {DatPhong.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract DatPhongDao datPhongDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "DatPhongDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
