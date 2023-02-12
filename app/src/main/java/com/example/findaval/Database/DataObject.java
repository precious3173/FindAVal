package com.example.findaval.Database;

import android.content.Context;

import androidx.room.Room;

class DataObject {

 public static ProfileDatabase Appdata(Context context){

     ProfileDatabase db = Room.databaseBuilder(

             context, ProfileDatabase.class, "profile"
     ).build();

       return db ;
    }
}
