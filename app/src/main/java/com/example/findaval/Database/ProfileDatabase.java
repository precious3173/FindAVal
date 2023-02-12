package com.example.findaval.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;


@Database(entities = {ProfileEntity.class}, version = 1)


@TypeConverters
public abstract class ProfileDatabase extends RoomDatabase {
   public abstract ProfileDao profileDao();
}
