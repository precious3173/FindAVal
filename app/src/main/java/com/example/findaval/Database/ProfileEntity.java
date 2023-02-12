package com.example.findaval.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

@Entity(tableName = "profile")
public class ProfileEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String firstName;

    @ColumnInfo(name = "status")
    public String status;


    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    public byte[] image;
}
