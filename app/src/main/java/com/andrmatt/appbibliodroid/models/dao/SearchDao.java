package com.andrmatt.appbibliodroid.models.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.andrmatt.appbibliodroid.models.entities.SearchEntity;

import java.util.List;

@Dao
public interface SearchDao {
    @Query("SELECT * FROM searches WHERE userId LIKE :userId")
    List<SearchEntity> getSearches(String userId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SearchEntity search);

    @Query("DELETE FROM searches WHERE id LIKE :id")
    void delete(String id);
}
