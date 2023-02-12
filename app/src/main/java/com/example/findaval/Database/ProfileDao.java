package com.example.findaval.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProfileDao {

    @Insert
    void insertProfile(ProfileEntity profileEntity);

    @Update
    void UpdateProfile(ProfileEntity profileEntity);

    @Query("SELECT * FROM profile")
    List<ProfileEntity> getAll();


}
