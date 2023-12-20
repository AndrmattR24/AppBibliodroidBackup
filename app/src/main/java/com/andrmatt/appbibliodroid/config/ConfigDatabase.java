package com.andrmatt.appbibliodroid.config;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.andrmatt.appbibliodroid.models.dao.SearchDao;
import com.andrmatt.appbibliodroid.models.entities.SearchEntity;

@Database(entities = {SearchEntity.class}, version = 1, exportSchema = false)
public abstract class ConfigDatabase extends RoomDatabase {
    public abstract SearchDao getSearchDao();

    public static ConfigDatabase getDatabase(Application app) {
        return  Room.databaseBuilder(
                app.getApplicationContext(),
                ConfigDatabase.class,
                "books_db"
        ).allowMainThreadQueries().build();
    }
}
