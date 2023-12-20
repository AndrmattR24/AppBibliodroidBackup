package com.andrmatt.appbibliodroid.models.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.andrmatt.appbibliodroid.config.ConfigDatabase;
import com.andrmatt.appbibliodroid.models.dao.SearchDao;
import com.andrmatt.appbibliodroid.models.entities.SearchEntity;

import java.util.List;

public class SearchRepository {
    ConfigDatabase database;
    SearchDao searchDao;

    public SearchRepository(Application application) {
        database = ConfigDatabase.getDatabase(application);
        searchDao = database.getSearchDao();
    }

    public void insertSearch(SearchEntity search) {
        searchDao.insert(search);
    }

    public List<SearchEntity> getSearches(String userId) {
        return searchDao.getSearches(userId);
    }

    public void deleteSearch(String id) {
        searchDao.delete(id);
    }
}
