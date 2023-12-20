package com.andrmatt.appbibliodroid.models.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.andrmatt.appbibliodroid.models.entities.SearchEntity;

import java.util.List;

@Dao
public interface SearchDao {
    @Query("SELECT * FROM searches WHERE userId LIKE :userId")
    List<SearchEntity> getSearches(String userId);
}
