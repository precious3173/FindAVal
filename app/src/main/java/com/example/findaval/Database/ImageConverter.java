package com.example.findaval.Database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class ImageConverter {

    @TypeConverter
    public static byte[] ImageToByteArray (Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG,0,byteArrayOutputStream);

        byte[] profilePix = byteArrayOutputStream.toByteArray();
        return profilePix;
    }

    public static Bitmap byteArrayToBitmap (byte[] byteArray){
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        return bitmap;
    }
}
