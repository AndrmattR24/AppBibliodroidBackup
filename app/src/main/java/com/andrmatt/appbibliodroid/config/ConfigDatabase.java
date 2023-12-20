package com.andrmatt.appbibliodroid.config;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.andrmatt.appbibliodroid.models.dao.SearchDao;
import com.andrmatt.appbibliodroid.models.entities.SearchEntity;

@Database(entities = {SearchEntity.class}, version = 1)
public abstract class ConfigDatabase extends RoomDatabase {
    public abstract SearchDao getSearchDao();
}
