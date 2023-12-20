package com.andrmatt.appbibliodroid.config;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.andrmatt.appbibliodroid.models.dao.SearchDao;
import com.andrmatt.appbibliodroid.models.entities.SearchEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SearchEntity.class}, version = 1, exportSchema = false)
public abstract class ConfigDatabase extends RoomDatabase {
    public abstract SearchDao getSearchDao();

    private static volatile ConfigDatabase database;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ConfigDatabase getDatabase(final Context context) {
        if (database == null) {
            synchronized (ConfigDatabase.class) {
                database = Room.databaseBuilder(context.getApplicationContext(), ConfigDatabase.class,"books_database").build();
            }
        }
        return database;
    }
}
