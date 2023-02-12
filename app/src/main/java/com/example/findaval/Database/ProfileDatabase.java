package com.example.findaval.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ProfileEntity.class}, version = 1)
public abstract class ProfileDatabase extends RoomDatabase {
   public abstract ProfileDao profileDao();
}
