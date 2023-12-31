package com.andrmatt.appbibliodroid.models.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.UUID;

@Entity(tableName = "searches")
public class SearchEntity {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "userId")
    private String userId;

    public SearchEntity(String content, String userId) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.userId = userId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

