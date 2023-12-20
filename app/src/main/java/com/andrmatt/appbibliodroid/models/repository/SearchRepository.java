package com.andrmatt.appbibliodroid.models.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.andrmatt.appbibliodroid.config.ConfigDatabase;
import com.andrmatt.appbibliodroid.models.dao.SearchDao;
import com.andrmatt.appbibliodroid.models.entities.SearchEntity;

import java.util.List;

public class SearchRepository {
    ConfigDatabase database;
    SearchDao searchDao;
    private LiveData<List<SearchEntity>> listSearches;

    public SearchRepository(Application application) {
        database = ConfigDatabase.getDatabase(application);
        searchDao = database.getSearchDao();
    }

    public void insertSearch(SearchEntity search) {
        ConfigDatabase.databaseWriteExecutor.execute(() -> searchDao.insert(search));
    }
}
