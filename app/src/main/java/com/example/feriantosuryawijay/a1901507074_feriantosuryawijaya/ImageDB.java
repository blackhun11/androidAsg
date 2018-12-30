package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.DBHelper.FIELD_IMAGE_ID;
import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.DBHelper.FIELD_IMAGE_NAME;
import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.DBHelper.TABLE_IMAGES;

public class ImageDB {

    private DBHelper dbHelper;

    public ImageDB ( Context context ) {
        dbHelper = new DBHelper(context);
    }

    public void store ( Image image ) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_IMAGE_ID , image.getId());
        contentValues.put(FIELD_IMAGE_NAME , image.getName());
        db.insert(TABLE_IMAGES , null , contentValues);
        db.close();
    }

    public boolean checkImageExists () {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_IMAGES , null , null , null , null , null , null);
        cursor.moveToFirst();
        boolean exists = cursor.getCount() != 0;
        cursor.close();
        return exists;
    }

    public List<Image> create () {

        List<Image> imageList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_IMAGES , null , null , null , null , null , null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(FIELD_IMAGE_ID));
            String name = cursor.getString(cursor.getColumnIndex(FIELD_IMAGE_NAME));
            Image image = new Image(id , name);
            imageList.add(image);
        }
        cursor.close();
        db.close();
        return imageList;
    }
}
