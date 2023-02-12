package com.example.findaval.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "profile")
public class ProfileEntity {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String firstName;

    @ColumnInfo(name = "status")
    public String status;

    @ColumnInfo(name = "image")
    public int image;
}
